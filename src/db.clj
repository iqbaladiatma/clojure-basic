(ns db
  (:require [next.jdbc :as jdbc]
            [next.jdbc.sql :as sql]
            [next.jdbc.result-set :as rs]))

;; 1. Koneksi Database Utama
(def conn {:dbtype "sqlite" :dbname "data_aplikasi.db"})

;; 2. Fungsi Abstraksi: Ambil semua tim
(defn get-semua-tim []
  (sql/find-by-keys conn :tim_dev :all {:builder-fn rs/as-unqualified-maps}))

;; 3. Fungsi Abstraksi: Tambah tim baru
(defn insert-tim! [nama peran]
  (sql/insert! conn :tim_dev {:nama nama :peran peran}))