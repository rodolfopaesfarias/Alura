(ns hospital.aula1
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(defn adiciona-paciente
  [pacientes, paciente]
  (if-let [id  (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "Paciente não possui id" {:paciente paciente}))))

(defn adiciona-visitas
  [visitas, paciente, novas-visitas]
  (if (contains? visitas paciente)
    (update visitas paciente concat novas-visitas)
    (assoc visitas paciente novas-visitas)))

(defn imprime-relatorio-de-paciente
  [visitas, paciente]
  (println "Visitas do paciente" paciente "são " (get visitas paciente)))

(defn testa-uso-pacientes []
  (let [rodolfo {:id 7, :nome "Rodolfo"}
        bruna {:id 3, :nome "Bruna"}
        levi {:id 1, :nome "Levi"}
        pacientes (reduce adiciona-paciente {} [rodolfo, bruna, levi])
        visitas {}
        visitas (adiciona-visitas visitas 7 ["07/07/2021"])
        visitas (adiciona-visitas visitas 3 ["01/02/2022"])]
    (pprint pacientes)
    (pprint visitas)
    (imprime-relatorio-de-paciente visitas 7))
  )

(testa-uso-pacientes)

(s/set-fn-validation! true)

(pprint (s/validate Long 15))
;(pprint (s/validate Long "Rodolfo"))

(s/defn teste-simples
  [x :- Long]
  (pprint x))

(teste-simples 15)
;(teste-simples "Rodolfo")

(s/defn imprime-relatorio-de-paciente
  [visitas, paciente :- Long]
  (println "Visitas do paciente" paciente "são " (get visitas paciente)))

(testa-uso-pacientes)

(s/defn novo-paciente
  [id :- long, nome :- s/Str]
  {:id id, :nome nome})

(pprint (novo-paciente 15 "Carlos"))
;(pprint (novo-paciente "Carlos" 15))