(ns core
  (:require [ring.adapter.jetty :as jetty]
            [reitit.ring :as ring]
            [cheshire.core :as json])) ;; <--- Memanggil mesin JSON

;; 1. Handler Lama (Statis)
(defn handler-ping [_request]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string {:pesan "API menyala!" :status "sukses"})}) ;; Jauh lebih rapi!

;; 2. Handler Baru (Dinamis)
(defn handler-sapa [request]
  ;; Menyelam ke dalam Map request untuk mengambil parameter URL
  (let [nama-pengunjung (get-in request [:path-params :nama])
        ;; Kita rakit data response menggunakan Map biasa
        data-response {:pesan (str "Halo " nama-pengunjung ", selamat datang!")
                       :framework "Clojure + Reitit"
                       :siap-consume? true}]

    {:status 200
     :headers {"Content-Type" "application/json"}
     ;; Cheshire akan menyulap Map data-response menjadi string JSON dengan sempurna
     :body (json/generate-string data-response)}))

;; 3. Router yang diperbarui
(def app-router
  (ring/ring-handler
   (ring/router
    ["/api"
     ["/ping" {:get handler-ping}]
     ["/sapa/:nama" {:get handler-sapa}]]) ;; <--- Rute dinamis kita

   (ring/create-default-handler
    {:not-found (fn [_request]
                  {:status 404
                   :headers {"Content-Type" "application/json"}
                   :body (json/generate-string {:error "Rute 404 Not Found"})})})))

;; 4. Infrastruktur Server (Anti-Zombie)
(defonce server (atom nil))

(defn start-server []
  (when @server
    (println "Mematikan server lama...")
    (.stop @server))
  (println "🚀 Menyalakan server baru di port 8080...")
  (reset! server (jetty/run-jetty app-router {:port 8080 :host "0.0.0.0" :join? false}))
  (println "✅ Server sukses mengudara!"))
(start-server)