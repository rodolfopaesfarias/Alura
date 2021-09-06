(ns hospital.aula1
  (:use [clojure pprint])
  (:require [hospital.model :as h.model])
  (:require [hospital.logic :as h.logic]))

(defn simula-um-dia []
  (def hospital (h.model/novo-hospital))
  (def hospital (h.logic/chega-em hospital :espera "111"))
  (def hospital (h.logic/chega-em hospital :espera "222"))
  (def hospital (h.logic/chega-em hospital :espera "333"))
  (def hospital (h.logic/chega-em hospital :espera "444"))
  (def hospital (h.logic/chega-em hospital :espera "555"))
  ;;(def hospital (h.logic/chega-em hospital :espera "666"))
  ;;(def hospital (h.logic/atende hospital :espera))
  (pprint hospital))

(simula-um-dia)
