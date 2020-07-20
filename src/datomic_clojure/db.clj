(ns datomic-clojure.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-url "datomic:dev://localhost:4334/person")

;create database if not exists and return connection
(defn open-connect []
  (d/create-database db-url)
  (d/connect db-url))

;drop database if necessary
(defn drop-database
  (d/delete-database db-url))