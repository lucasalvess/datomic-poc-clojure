(ns datomic-clojure.service.person-service
  (:require [datomic.api :as d]
            [datomic-clojure.db :as db]
            [datomic-clojure.repository.person-repository :as repository]
            [datomic-clojure.model.person-model :as model]))

(def conn (db/open-connect))

(defn get-persons
  (repository/find-all-persons (d/db conn)))

(defn get-person [cpf]
  (repository/find-by-cpf (d/db conn) cpf))

(defn add-person [cpf name gender email birth_date profession patrimony]
  (let [person (model/create-person cpf, name, gender, email, birth_date, profession, patrimony)]
  (repository/save conn person)))

(defn delete-person [id]
  (repository/delete conn id))

(defn update-person [id cpf name  gender email birth_date profession patrimony]
  (repository/update conn, id, cpf, name, gender, email, birth_date, profession, patrimony))