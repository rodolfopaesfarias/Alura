(ns hospital.logic)

(defn cabe-na-fila?
  [hospital, departamento]
  (when-let
    [fila (get hospital departamento)]
    (-> fila
        count
        (< 5))))
