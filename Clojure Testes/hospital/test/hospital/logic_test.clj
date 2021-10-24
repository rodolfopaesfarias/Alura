(ns hospital.logic-test
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]))

(deftest cabe-na-fila?-test

  (testing "quando fila vazia"
    (is (cabe-na-fila? {:espera []}, :espera)))

  (testing "quando cabe na fila e tem gente"
    (is (cabe-na-fila? {:espera [1, 2]}, :espera))
    (is (cabe-na-fila? {:espera [1, 3, 3, 4]}, :espera)))

  (testing "quando fila cheia"
    (is (not (cabe-na-fila? {:espera [1, 2, 3, 4, 5]} :espera))))

  (testing "quando fila mais que cheia"
    (is (not (cabe-na-fila? {:espera [1, 2, 3, 4, 5, 6]} :espera))))

  (testing "quando departamento não existe"
    (is (not (cabe-na-fila? {:espera []}, :raio-x)))))