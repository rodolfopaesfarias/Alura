(ns hospital.aula2
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def Paciente
  {:id s/Num,
   :nome s/Str})

(pprint (s/explain Paciente))
(pprint (s/validate Paciente {:id 15, :nome "Rodolfo"}))
;(pprint (s/validate Paciente {:id "15", :nome "Rodolfo"}))

(s/defn novo-paciente :- Paciente
  [id :- s/Num,
   nome :- s/Str
   ]
  {:id id, :nome nome})

(pprint (novo-paciente 15 "Rodolfo"))
;(pprint (novo-paciente "15" "Rodolfo"))

(defn estritamente-positivo? [x]
  (> x 0))

(pprint (estritamente-positivo? -10))

(def EstritamentePositivo
  (s/pred estritamente-positivo? 'estritamente-positivo))

(pprint (s/validate EstritamentePositivo 10))

(def Paciente
  {:id (s/constrained s/Int estritamente-positivo?),
   :nome s/Str})

(s/defn novo-paciente :- Paciente
  [id :- s/Num,
   nome :- s/Str
   ]
  {:id id, :nome nome})

(pprint (novo-paciente 15 "Rodolfo"))
;(pprint (novo-paciente 0 "Rodolfo"))

(def Paciente
  {:id (s/constrained s/Int pos?),
   :nome s/Str})

(s/defn novo-paciente :- Paciente
  [id :- s/Num,
   nome :- s/Str
   ]
  {:id id, :nome nome})

(pprint (novo-paciente 15 "Rodolfo"))
;(pprint (novo-paciente 0 "Rodolfo"))

(def Paciente
  {:id (s/constrained s/Int #(> % 0) 'estritamente-positivo),
   :nome s/Str})

(s/defn novo-paciente :- Paciente
  [id :- s/Num,
   nome :- s/Str
   ]
  {:id id, :nome nome})

(pprint (novo-paciente 15 "Rodolfo"))
(pprint (novo-paciente 0 "Rodolfo"))