(ns datomic-clojure.examples.querys
  (:require [datomic.api :as d]
            [datomic-clojure.db :as db]
            [datomic-clojure.model.person-model :as model]))
;  Query examples
;-------------------------------------------------
;define an connection
(def conn (db/open-connect))
(db/create-schema conn)

;return entity id in datom with person name in attr
(d/q '[:find ?entity
               :where [?entity :person/name]] (d/db conn))

;simple generic query map
(defn find-all-persons-map [db]
  (d/q '[:find (pull ?entity [*])
         :where [?entity :person/name]] db))

;simple query map with parameters ($ is = db)
(defn find-map-by-cpf [db cpf]
  (d/q '[:find (pull ?entity [*])
         :in $ ?cpf
         :where [?entity :person/name ?name]] db cpf))

;explicit pull field map
(defn find-all-persons-named-attr [db]
 (d/q '[:find (pull ?entity [:person/nome, :person/cpf])
        :where [?entity :person/name]] db))

;explicit fields return vector
(defn find-all-persons-with-fields [db]
  (d/q '[:find ?entity ?name, ?profession, ?patrimony, ?birth-date, ?cpf
         :where [?entity :person/name ?name]
                [?entity :person/profession ?profession]
                [?entity :person/patrimony ?patrimony]
                [?entity :person/birth_date ?birth-date]
                [?entity :person/cpf ?cpf]] db))

;explicit return key map
(defn find-all-persons-with-kwys [db]
  (d/q '[:find ?entity ?name, ?profession, ?patrimony, ?birth-date, ?cpf
         :keys  [db/entity, :person/name ,:person/profession,:person/patrimony, :person/birth_date, :person/cpf]
         :where [?entity :person/name ?name]
                [?entity :person/profession ?profession]
                [?entity :person/patrimony ?patrimony]
                [?entity :person/birth_date ?birth-date]
                [?entity :person/cpf ?cpf]] db))

