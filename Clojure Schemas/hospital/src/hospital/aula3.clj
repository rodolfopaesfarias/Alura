(ns hospital.aula3
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def PosInt
  (s/pred pos-int? 'inteiro-positivo))

(def Paciente
  {:id PosInt,
   :nome s/Str})

(s/defn novo-paciente :- Paciente
  [id :- PosInt,
   nome :- s/Str]
  {:id id, :nome nome})

(pprint (novo-paciente 7, "Rodolfo"))
;(pprint (novo-paciente 0, "Rodolfo"))

(defn maior-ou-igual-a-zero
  [x]
  (>= x 0))

(def ValorFinanceiro
  (s/constrained s/Num maior-ou-igual-a-zero))

(def Pedido
  {:paciente Paciente,
   :valor ValorFinanceiro,
   :procedimento s/Keyword})

(s/defn novo-pedido
  [paciente :- Paciente,
   valor :- ValorFinanceiro,
   procedimento :- s/Keyword]
  {:paciente paciente, :valor valor, :procedimento procedimento})

(pprint (novo-pedido (novo-paciente 7, "Rodolfo") 7.87 :raio-x))
(pprint (novo-pedido (novo-paciente 7, "Rodolfo") 0 :raio-x))
;(pprint (novo-pedido (novo-paciente 7, "Rodolfo") -5 :raio-x))

(def Plano [s/Keyword])

(def Paciente
  {:id PosInt,
   :nome s/Str,
   :plano Plano})

(pprint (s/validate Paciente {:id 15, :nome "Rodolfo", :plano [:raio-x]}))
(pprint (s/validate Paciente {:id 15, :nome "Rodolfo", :plano []}))
(pprint (s/validate Paciente {:id 15, :nome "Rodolfo", :plano [:raio-x, :exame-de-sangue]}))
;(pprint (s/validate Paciente {:id 15, :nome "Rodolfo", :plano [nil]}))
