(ns datomic-clojure.repository.person-repository
  (:require [datomic.api :as d]
            [datomic-clojure.db :as db]
            [datomic-clojure.model.person-model :as model]))


(defn find-all-persons [db]
  (d/q '[:find (pull ?entity [*])
         :where [?entity :person/name]] db))

(defn find-by-cpf [db cpf]
  (d/q '[:find (pull ?entity [*])
         :in $ ?cpf
         :where [?entity :person/name ?name]] db cpf))

(defn save [conn person]
  @(d/transact conn person))

