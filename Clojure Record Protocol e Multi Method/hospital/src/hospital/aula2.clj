(ns hospital.aula2
  (:use clojure.pprint))

(defrecord PacienteParticular [id, nome, nascimento])
(defrecord PacientePlanoDeSaude [id, nome, nascimento, plano])

(defprotocol Cobravel
  (deve-solicitar-autorizacao? [paciente, procedimento, valor]))

(extend-type PacienteParticular
  Cobravel
  (deve-solicitar-autorizacao? [paciente, procedimento, valor]
    (>= valor 50)))

(extend-type PacientePlanoDeSaude
  Cobravel
  (deve-solicitar-autorizacao? [paciente, procedimento, valor]
    (let [plano (:plano paciente)]
      (not (some #(= % procedimento) plano)))))

(let
  [pacienteParticular (->PacienteParticular 1, "Rodolfo", "07/07/1986")
  pacientePlanoDeSaude (->PacientePlanoDeSaude 1, "Rodolfo", "07/07/1986", [:raio-x, :endoscopia])]
  (pprint (deve-solicitar-autorizacao? pacienteParticular, :raio-x, 50))
  (pprint (deve-solicitar-autorizacao? pacienteParticular, :raio-x, 45))
  (pprint (deve-solicitar-autorizacao? pacienteParticular, :raio-x, 45))
  (pprint (deve-solicitar-autorizacao? pacientePlanoDeSaude, :raio-x, 45))
  (pprint (deve-solicitar-autorizacao? pacientePlanoDeSaude, :cirurgia, 45)))