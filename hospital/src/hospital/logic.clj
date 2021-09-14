(ns hospital.logic)

(defn cabe-na-fila? [hospital departamento]
  (-> hospital
      (get departamento)
      count
      (< 5)))

(defn chega-em [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (ex-info "Fila Cheia" {:tentando-adicionar pessoa}))))

(defn atende [hospital departamento]
  (update hospital departamento pop))

(defn proxima
  [hospital departamento]
  (-> hospital
      departamento
      peek))

(defn transfere
  [hospital de para]
  (let [pessoa (proxima hospital de)]
    (-> hospital
        (atende de)
        (chega-em para pessoa))))
