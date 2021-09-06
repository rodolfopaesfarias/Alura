(ns hospital.colecoes
  (:use [clojure pprint]))

(defn testa-fila []
  (let [espera (conj clojure.lang.PersistentQueue/EMPTY 111 222)]
  (println (seq espera))
  (pprint espera)
  (pprint (conj espera 333))
  (pprint (peek espera))
  (pprint (pop espera))))

(testa-fila)