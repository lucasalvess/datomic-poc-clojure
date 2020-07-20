(ns datomic-clojure.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [datomic-clojure.db :as db]
            [datomic-clojure.person-model :as model]))

;define an connection
(def conn (db/open-connect))

(db/create-schema conn)

;define a map with the person model
(let [person
  (model/create-person "111.111.111-11", "Gandalf the Grey ", "M",
                       "gandof@middleearth.com", (new java.util.Date), "Mage", 10000.00M)]

;receive the connection and an sequence ([map])
(pprint (d/transact conn [person])))

; received the database state (SNAPSHOT)
(def db (d/db conn))

;receive identity id of datoms that have an person name
(pprint (d/q '[:find ?entity
               :where [?entity :person/name]] db))

