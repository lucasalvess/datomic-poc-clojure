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
  (def result @(d/transact conn [person])))

;return entity id
(def entity-id (-> result :tempids vals first))

;received the database state (SNAPSHOT)
(def db (d/db conn))

;update patrimony
(pprint @(d/transact conn [[:db/add entity-id :person/patrimony 0.10M]]))

;remove profession datom
(pprint @(d/transact conn [[:db/retract entity-id :person/profession "Mage"]]))

(db/drop-database)