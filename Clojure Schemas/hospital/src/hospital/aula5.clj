(ns hospital.aula5
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def PosInt (s/pred pos-int? 'inteiro-positivo))

(def Plano [s/Keyword])

(def Paciente
  {:id PosInt,
   :nome s/Str,
   :plano Plano,
   (s/optional-key :nascimento) s/Str})

(def Pacientes
  {PosInt, Paciente})

(def Visitas
  {PosInt, [s/Str]})

(s/defn adiciona-paciente :- Pacientes
  [pacientes :- Pacientes, paciente :- Paciente]
  (let [id  (:id paciente)]
    (assoc pacientes id paciente)))

(s/defn adiciona-visitas :- Visitas
  [visitas :- Visitas, paciente :- PosInt, novas-visitas :- [s/Str]]
  (if (contains? visitas paciente)
    (update visitas paciente concat novas-visitas)
    (assoc visitas paciente novas-visitas)))

(s/defn imprime-relatorio-de-paciente
  [visitas :- Visitas, paciente :- PosInt]
  (println "Visitas do paciente" paciente "são " (get visitas paciente)))

(defn testa-uso-pacientes []
  (let [rodolfo {:id 7, :nome "Rodolfo", :plano []}
        bruna {:id 3, :nome "Bruna", :plano []}
        levi {:id 1, :nome "Levi", :plano []}
        pacientes (reduce adiciona-paciente {} [rodolfo, bruna, levi])
        visitas {}
        visitas (adiciona-visitas visitas 7 ["07/07/2021"])
        visitas (adiciona-visitas visitas 3 ["01/02/2022"])
        ]
    (pprint pacientes)
    (pprint visitas)
    (imprime-relatorio-de-paciente visitas 3)
    )
  )

(testa-uso-pacientes)