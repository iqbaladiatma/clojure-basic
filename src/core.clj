(ns core
  (:require [ring.adapter.jetty :as jetty]
            [reitit.ring :as ring]))

(defn handler-ping [_request]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "{\"pesan\": \"Alhamdulillah, API Clojure-ku menyala!\", \"status\": \"sukses\"}"})

(def app-router
  (ring/ring-handler
   (ring/router
    ["/api"
     ["/ping" {:get handler-ping}]])

   ;; Ini adalah penangkap rute default (404 Not Found)
   (ring/create-default-handler
    {:not-found (fn [_request]
                  {:status 404
                   :headers {"Content-Type" "application/json"}
                   :body "{\"error\": \"Waduh, rute ini tidak ada di peta (404)!\"}"})})))

;; 3. Ini adalah 'kotak penyimpanan' untuk server kita
(defonce server (atom nil))

;; 4. Fungsi sakti untuk merestart server dengan aman
(defn start-server []
  (when @server
    (println "Mematikan server lama...")
    (.stop @server))

  (println "🚀 Menyalakan server baru di port 8080...")
  (reset! server (jetty/run-jetty app-router {:port 8080 :host "0.0.0.0" :join? false}))
  (println "✅ Server sukses mengudara!"))
(start-server)
