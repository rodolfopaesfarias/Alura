(ns hospital.aula6
  (:require [hospital.model :as h.model])
  (:use [clojure pprint]))

(defn cabe-na-fila? [fila]
  (-> fila
      count
      (< 5)))

(defn chega-em
  [fila pessoa]
  (if (cabe-na-fila? fila)
    (conj fila pessoa)
    (throw (ex-info "Fila cheia" {:tentando-adicionar pessoa}))))

(defn chega-em!
  [hospital pessoa]
  (let [fila (get hospital :espera)]
    (alter fila chega-em pessoa)))

(defn simula-um-dia
  []
  (let [hospital {:espera (ref h.model/fila-vazia)
                  :laboratorio1 (ref h.model/fila-vazia)
                  :laboratorio2 (ref h.model/fila-vazia)
                  :laboratorio3 (ref h.model/fila-vazia)
                  }]
    (dosync
      (chega-em! hospital "rodolfo"))
  (pprint hospital)))

;(simula-um-dia)

(defn async-chega-em!
  [hospital pessoa]
  (future (Thread/sleep (rand 5000))
          (dosync
            (println "tentando adicionar " pessoa)
            (chega-em! hospital pessoa))))

(defn async-simula-um-dia
  []
  (let [hospital {:espera (ref h.model/fila-vazia)
                  :laboratorio1 (ref h.model/fila-vazia)
                  :laboratorio2 (ref h.model/fila-vazia)
                  :laboratorio3 (ref h.model/fila-vazia)
                  }]
    (def futures (mapv #(async-chega-em! hospital %) (range 10)))
    (future
      (dotimes [n 4]
        (Thread/sleep 2000)
        (pprint hospital)))))

(async-simula-um-dia)

