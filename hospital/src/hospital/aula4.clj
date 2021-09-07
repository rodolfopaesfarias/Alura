(ns hospital.aula4
  (:require [hospital.logic :as h.logic])
  (:use [clojure pprint]))

(defn chega-em-sem-malvadeza!
  [hospital pessoa]
  (swap! hospital h.logic/chega-em :espera pessoa))

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (hospital.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-em-sem-malvadeza! hospital "111"))))
    (.start (Thread. (fn [] (chega-em-sem-malvadeza! hospital "222"))))
    (.start (Thread. (fn [] (chega-em-sem-malvadeza! hospital "333"))))
    (.start (Thread. (fn [] (chega-em-sem-malvadeza! hospital "444"))))
    (.start (Thread. (fn [] (chega-em-sem-malvadeza! hospital "555"))))
    (.start (Thread. (fn [] (chega-em-sem-malvadeza! hospital "666"))))
    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

(simula-um-dia-em-paralelo)
