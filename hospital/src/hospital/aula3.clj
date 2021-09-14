(ns hospital.aula3
  (:use [clojure pprint])
  (:require [hospital.model :as h.model])
  (:require [hospital.logic :as h.logic]))

(defn testa-atomao
  []
  (let [hospital-farias (atom { :espera h.model/fila-vazia})]
    (pprint @hospital-farias)

    (swap! hospital-farias assoc :laboratorio1 h.model/fila-vazia)
    (pprint @hospital-farias)

    (swap! hospital-farias update :espera conj "111")
    (pprint hospital-farias)

    (swap! hospital-farias update :laboratorio1 conj "222")
    (pprint hospital-farias)))


(testa-atomao)