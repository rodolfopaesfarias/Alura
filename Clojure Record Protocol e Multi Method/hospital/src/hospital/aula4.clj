(ns hospital.aula4
  (:use clojure.pprint))

(defrecord PacienteParticular [id, nome, nascimento, situacao])
(defrecord PacientePlanoDeSaude [id, nome, nascimento, plano, situacao])

(defn tipo-autorizador
  [pedido]
  (let [paciente (:paciente pedido)
        situacao (:situacao paciente)
        urgencia? (= :urgente situacao)]
    (if urgencia?
      :sempre-autorizado
      (class paciente))))

(defmulti deve-assinar-pre-autorizacao-pedido? tipo-autorizador)

(defmethod deve-assinar-pre-autorizacao-pedido? :sempre-autorizado [pedido]
  false)

(defmethod deve-assinar-pre-autorizacao-pedido? PacienteParticular [pedido]
  (>= (:valor pedido 0) 50))

(defmethod deve-assinar-pre-autorizacao-pedido? PacientePlanoDeSaude [pedido]
  (not (some #(= % (:procedimento pedido)) (:plano (:paciente pedido)))))

(let
  [pacienteParticular (->PacienteParticular 1, "Rodolfo", "07/07/1986", :normal)
   pacientePlanoDeSaude (->PacientePlanoDeSaude 1, "Rodolfo", "07/07/1986", [:raio-x, :endoscopia], :normal)]
  (pprint (deve-assinar-pre-autorizacao-pedido? {:paciente pacienteParticular, :valor 1000}))
  (pprint (deve-assinar-pre-autorizacao-pedido? {:paciente pacientePlanoDeSaude, :valor 1000, :procedimento :raio-x})))
