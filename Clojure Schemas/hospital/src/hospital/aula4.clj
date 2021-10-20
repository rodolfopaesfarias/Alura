(ns hospital.aula4
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

(pprint (s/validate Paciente {:id 15, :nome "Rodolfo", :plano [:raio-x]}))
(pprint (s/validate Paciente {:id 15, :nome "Rodolfo", :plano []}))
(pprint (s/validate Paciente {:id 15, :nome "Rodolfo", :plano [:raio-x, :exame-de-sangue]}))
;(pprint (s/validate Paciente {:id 15, :nome "Rodolfo", :plano [nil]}))

(def Pacientes
  {PosInt, Paciente})

(pprint (s/validate Pacientes {15, {:id 7, :nome "Rodolfo", :plano [], :nascimento "07/07/1986"}}))

(let [rodolfo {:id 7, :nome "Rodolfo", :plano [], :nascimento "07/07/1986"},
      bruna {:id 3, :nome "Bruna", :plano [:raio-x], :nascimento "05/02/2000"}]
  (pprint (s/validate Pacientes {7 rodolfo}))
  (pprint (s/validate Pacientes {7 rodolfo, 3 bruna})))