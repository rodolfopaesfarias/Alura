(ns hospital.aula1
  (:use clojure.pprint))

(defn adiciona-paciente
  [pacientes paciente]
  (if-let [id (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "Paciente sem ID" {:paciente paciente}))))

(defn testa-uso-de-pacientes
  []
  (let [pacientes {}
        rodolfo {:id 7, :nome "Rodolfo", :nascimento "07/07/1986"}
        carlos {:id 9, :nome "Carlos", :nascimento "10/01/2000"}
        levi { :nome "Levi", :nascimento "26/12/1986"}]
    (pprint (adiciona-paciente pacientes rodolfo))
    (pprint (adiciona-paciente pacientes carlos))
    (pprint (adiciona-paciente pacientes levi))))

(testa-uso-de-pacientes)