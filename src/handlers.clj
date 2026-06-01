(ns handlers
  (:require [cheshire.core :as json]
            [db :as db])) ;; <--- Mengimpor file db.clj yang baru kita buat!

(defn get-tim [_request]
  ;; Memanggil fungsi dari namespace db
  (let [data (db/get-semua-tim)]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string data)}))

(defn post-tim [request]
  (let [body-string (slurp (:body request))
        body-map (json/parse-string body-string true)
        nama (:nama body-map)
        peran (:peran body-map)]

    ;; Memanggil fungsi insert dari namespace db
    (db/insert-tim! nama peran)

    {:status 201
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string {:pesan (str "Berhasil menambahkan " nama)
                                  :status "sukses"})}))
;; Simulasi fungsi algoritma yang berjalan sangat lama
(defn jalankan-analisis-saham [kode-saham]
  (println "⏳ [BACKGROUND] Memulai proses screening algoritma Fatamma untuk saham:" kode-saham)
  ;; Thread/sleep akan menunda/membekukan proses ini selama 5 detik
  (Thread/sleep 5000)
  (println "✅ [BACKGROUND] Selesai! Hasil kalkulasi teknikal untuk" kode-saham "sudah siap."))

;; Handler untuk menerima request dari user
(defn post-kalkulasi [request]
  (let [body-string (slurp (:body request))
        body-map (json/parse-string body-string true)
        saham (:kode body-map)]
    
    ;; KEAJAIBAN TERJADI DI SINI: 
    ;; Kita membungkus pemanggilan fungsi ke dalam 'future'
    (future (jalankan-analisis-saham saham))
    
    ;; API akan langsung mengeksekusi baris di bawah ini tanpa menunggu 'future' selesai!
    {:status 202 ;; 202 Accepted (Diterima dan sedang diproses)
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string {:pesan (str "Analisis untuk " saham " sedang diproses di server.")
                                  :status "processing"})}))