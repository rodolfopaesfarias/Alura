(ns hospital.aula5
  (:use clojure.pprint))

(defn tipo-autorizador  [pedido]
  (let [paciente (:paciente pedido)
        situacao (:situacao paciente)]
    (cond (= :urgente situacao) :sempre-autorizado
          (contains? paciente :plano) :plano-de-saude
          :else :valor-minimo)))

(defmulti deve-assinar-pre-autorizacao? tipo-autorizador)

(defmethod deve-assinar-pre-autorizacao? :sempre-autorizado [pedido]
  (pprint "sempre autorizado")
  false)

(defmethod deve-assinar-pre-autorizacao? :plano-de-saude [pedido]
  (pprint "plano saude")
  (not (some #(= % (:procedimento pedido)) (:plano (:paciente pedido)))))

(defmethod deve-assinar-pre-autorizacao? :valor-minimo [pedido]
  true
  (pprint "valor minimo")
  (>= (:valor pedido 0) 50))

(let
  [paciente-particular {:id 1, :nome "Rodolfo", :data-nascimento "07/07/1986", :situacao :normal}
   pedido-particular {:paciente paciente-particular, :valor 1000, :procedimento :coleta-de-sangue}
   paciente-plano-de-saude {:id 1, :nome "Rodolfo", :data-nascimento "07/07/1986", :situacao :normal, :plano [:raio-x, :ultrassom, :coleta-de-sangue]}
   pedido-plano-saude {:paciente paciente-plano-de-saude, :valor 1000, :procedimento :coleta-de-sangue}
   paciente-urgente {:id 1, :nome "Rodolfo", :data-nascimento "07/07/1986", :situacao :urgente, :plano [:raio-x, :ultrassom, :coleta-de-sangue]}
   pedido-urgente {:paciente paciente-urgente, :valor 1000, :procedimento :coleta-de-sangue}]

  (pprint (deve-assinar-pre-autorizacao? pedido-particular))
  (pprint (deve-assinar-pre-autorizacao? pedido-plano-saude))
  (pprint (deve-assinar-pre-autorizacao? pedido-urgente))
  )
