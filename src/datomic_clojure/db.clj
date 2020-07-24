(ns datomic-clojure.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-url "datomic:free://localhost:4334/person?password=admin")

;create database if not exists and return connection
(defn open-connect []
  (d/create-database db-url)
  (d/connect db-url))

;drop database if necessary
(defn drop-database []
  (d/delete-database db-url))

;declare person schema
(def schema [{
              :db/ident       :person/cpf
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Document number"
              :db/unique      :db.unique/identity
              }
             {
              :db/ident       :person/name
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              }
             {
              :db/ident       :person/birth_date
              :db/valueType   :db.type/instant
              :db/cardinality :db.cardinality/one
              }
             {
              :db/ident       :person/email
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              }
             {
              :db/ident       :person/gender
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              }
             {
              :db/ident       :person/patrimony
              :db/valueType   :db.type/bigdec
              :db/cardinality :db.cardinality/one
              }
             {
              :db/ident       :person/profession
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              }
             ])

;create schema
(defn create-schema [conn]
  (pprint (d/transact conn schema)))
