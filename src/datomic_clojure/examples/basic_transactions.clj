(ns datomic-clojure.examples.basic-transactions
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [datomic-clojure.db :as db]
            [datomic-clojure.model.person-model :as model]))

;define an connection
(def conn (db/open-connect))
(db/create-schema conn)

;define a map with the person model
(let [person
      (model/create-person "111.111.111-11", "Gandalf the Grey ", "M",
                           "gandof@middleearth.com", (new java.util.Date), "Mage", 10000.00M)]

  ;receive the connection and an map
  (pprint @(d/transact conn [person])))

;received the database state (SNAPSHOT)
(def db (d/db conn))


;return entity id
(def id-entity (d/q '[:find ?entity
                      :where [?entity :person/name]] db))

;update patrimony
(pprint @(d/transact conn [[:db/add id-entity :person/patrimony 0.1M]]))

;remove profession datom
(pprint @(d/transact conn [[:db/retract id-entity :person/profession "Baggins"]]))
(db/drop-database)