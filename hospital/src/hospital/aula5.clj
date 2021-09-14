(ns hospital.aula5
  (:require [hospital.logic :as h.logic])
  (:use [clojure pprint]))

(defn chega-em-sem-malvadeza!
  [hospital pessoa]
  (swap! hospital h.logic/chega-em :espera pessoa))

(defn starta-thread-chegada
  [hospital pessoa]
  (.start (Thread. (fn [] (chega-em-sem-malvadeza! hospital pessoa))))
  )

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (hospital.model/novo-hospital))
        pessoas ["111", "222", "333", "444", "555", "666"]
        starta (partial starta-thread-chegada hospital)]

    (mapv starta pessoas)

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

(simula-um-dia-em-paralelo)
