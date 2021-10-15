(ns hospital.aula3
  (:use clojure.pprint)
  (:require [hospital.logic :as h.logic]))

(defn carrega-paciente [id]
  (println "Carregando " id)
  (Thread/sleep 1000)
  { :id id, :carregado-em (h.logic/agora)})

;(pprint (carrega-paciente 15))

(defn carrega-se-nac-existe
  [cache id carregadora]
  (if (contains? cache id)
    cache
    (let [novo (carregadora id)]
      (assoc cache id novo))))

;(pprint (carrega-se-nac-existe {}, 15, carrega-paciente))
;(pprint (carrega-se-nac-existe { 15 { :id 15}}, 15, carrega-paciente))

(defprotocol Carregavel
  (carrega! [this id]))

(defrecord Cache
  [cache carregadora]

  Carregavel
  (carrega! [this id]
    (swap! cache carrega-se-nac-existe id carregadora)
    (get @cache id)))

(def pacientes (->Cache (atom {}), carrega-paciente))
(pprint pacientes)
(carrega! pacientes 15)
(carrega! pacientes 30)
(carrega! pacientes 15)
(pprint pacientes)
