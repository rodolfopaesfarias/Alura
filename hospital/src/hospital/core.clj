(ns hospital.core
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]))

(let [novo-hospital (h.model/novo-hospital)]
  (pprint novo-hospital))
