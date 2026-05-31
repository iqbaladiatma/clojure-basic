(ns core)

(:nama profil)

(defn sapa [nama]
  (str "Halo " nama ", awan Codespaces ini sangat nyaman!"))

(+ 100 50)

(sapa "Iqbal")

;; Kumpulan string biasa
(def keahlian ["Laravel" "Flutter" "Clojure"])

;; Eksekusi baris ini (Alt + Enter):
(conj keahlian "Agentic AI")
;; Outputnya akan muncul: ["Laravel" "Flutter" "Clojure" "Agentic AI"]

;; SEKARANG, eksekusi baris di bawah ini untuk melihat isi variabel aslinya:
keahlian


;; Vector bisa berisi tipe data campuran
(def campuran ["Roadbike", 2026, true, "Sim Racing"])

(def profil
  {:nama "Iqbal"
   :role "CTO"
   :lokasi "Solo"
   :makanan-favorit "Shawarma"})

;; Menambah kunci baru (:kampus) dan mengubah kunci lama (:makanan-favorit)
(assoc profil :kampus "Universitas Impian" :makanan-favorit "Nasi Padang")

(def bahasa-dikuasai #{"PHP" "Dart" "PHP"})
;; Coba evaluasi baris di atas, REPL pasti akan ngomel karena ada "PHP" dua kali!

(def rutinitas-pagi '("Bangun" "Gowes" "Ngopi di Cafe" "Coding"))

(def statistik {:skor 100 :nyawa 3})

;; Kita kurangi nyawanya. Fungsi 'dec' (decrement) mengurangkan angka dengan 1.
(update statistik :nyawa dec)


(let [skor-reading 750
      skor-math 780
      total-skor (+ skor-reading skor-math)]
  ;; Apapun yang ada di dalam blok kurung let ini bisa memakai variabel di atas
  (str "Total skormu adalah: " total-skor))

(if (> 1500 1400)
  "Skor luar biasa!"
  "Tetap semangat belajar!")

(defn cek-kesiapan-pendaftaran [skor-reading skor-math]
  (let [total-skor (+ skor-reading skor-math)
        target-minimum 1500
        aman? (>= total-skor target-minimum)]

    (if aman?
      (str "Skor " total-skor " sudah aman untuk submit ke kampus top!")
      (str "Skor " total-skor " masih kurang, ayo intensifkan lagi persiapannya."))))

(cek-kesiapan-pendaftaran 700 750) ;; Tes skor di bawah target
(cek-kesiapan-pendaftaran 760 790) ;; Tes skor di atas target

;; CONTOH BERSARANG (Sulit Dibaca)
(update (assoc (assoc {:kode "BSI" :harga 1000} :status "Syariah") :sektor "Keuangan") :harga + 500)

(def profil-emiten {:kode "GOTO" :harga 50})

(def emiten-terkini
  (-> profil-emiten
      (assoc :status "Syariah")         ;; Memasukkan :status baru
      (assoc :sektor "Teknologi")       ;; Memasukkan :sektor baru
      (update :harga + 15)))            ;; Menambah harga dengan 15

;; Coba panggil variabelnya untuk melihat hasil akhirnya:
emiten-terkini

(def daftar-saham
  [{:kode "BRIS" :halal? true :skor-analitik 85}
   {:kode "BBCA" :halal? false :skor-analitik 90}
   {:kode "AMRT" :halal? true :skor-analitik 75}])

(def hasil-screening
  (->> daftar-saham
       (filter :halal?)   ;; Langkah 1: Saring hanya yang nilai :halal?-nya true
       (map :kode)))      ;; Langkah 2: Ambil/ekstrak bagian :kode-nya saja

;; Panggil variabel ini untuk melihat outputnya:
hasil-screening

