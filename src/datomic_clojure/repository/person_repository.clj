(ns datomic-clojure.repository.person-repository
  (:require [datomic.api :as d]
            [datomic-clojure.db :as db]
            [datomic-clojure.model.person-model :as model]))

(defn find-all-persons [db]
  (d/q '[:find ?name, ?profession, ?patrimony, ?birth-date, ?cpf
         :where [?entity :person/name ?name]
                [?entity :person/profession ?profession]
                [?entity :person/patrimony ?patrimony]
                [?entity :person/birth_date ?birth-date]
                [?entity :person/cpf ?cpf]] db))


