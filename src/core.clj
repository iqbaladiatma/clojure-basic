(ns core
  (:require [ring.adapter.jetty :as jetty]
            [reitit.ring :as ring]
            [cheshire.core :as json]
            [handlers :as handlers])) ;; <--- Mengimpor file handlers.clj

(def app-router
  (ring/ring-handler
   (ring/router
    ["/api"
     ["/tim" {:get handlers/get-tim
              :post handlers/post-tim}]
     ;; --- TAMBAHAN RUTE BARU ---
     ["/kalkulasi" {:post handlers/post-kalkulasi}]])

   (ring/create-default-handler
    {:not-found (fn [_request]
                  {:status 404
                   :headers {"Content-Type" "application/json"}
                   :body (json/generate-string {:error "Rute tidak ditemukan"})})})))

(defonce server (atom nil))

(defn start-server []
  (when @server
    (println "Mematikan server lama...")
    (.stop @server))
  (println "🚀 Menyalakan server di port 8080...")
  (reset! server (jetty/run-jetty app-router {:port 8080 :host "0.0.0.0" :join? false}))
  (println "✅ Server sukses mengudara!"))
(start-server)