(ns hospital.aula5-2
  (:require [hospital.logic :as h.logic])
  (:require [hospital.model :as h.model])
  (:use [clojure pprint])  )

(defn chega-em!
  [hospital pessoa]
  (swap! hospital h.logic/chega-em :espera pessoa))

(defn transfere!
  [hospital de para]
  (swap! hospital h.logic/transfere de para))

(defn simula-um-dia
  []
  (let [hospital (atom (h.model/novo-hospital))]
    (chega-em! hospital "João")
    (chega-em! hospital "Maria")
    (chega-em! hospital "Carlos")
    (chega-em! hospital "Levi")
    (transfere! hospital :espera :laboratorio1)
    (pprint hospital)))

(simula-um-dia)