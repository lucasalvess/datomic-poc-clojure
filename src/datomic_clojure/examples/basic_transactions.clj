(ns datomic-clojure.examples.basic-transactions
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [datomic-clojure.db :as db]
            [datomic-clojure.model.person-model :as model]))



;define a map with the person model
(let [person
      (model/create-person "111.111.111-11", "Gandalf the Grey ", "M",
                           "gandof@middleearth.com", (new java.util.Date), "Mage", 10000.00M)]

;receive the connection and an sequence ([map])
  (pprint @(d/transact conn [person])))

;received the database state (SNAPSHOT)
(def db (d/db conn))


;return entity id and update and delete data
(let [person2
      (model/create-person "222.222.222-22", "Frodo Baggins", "M",
                           "frodo@middleearth.com", (new java.util.Date), "Baggins", 700.00M)
      result @(d/transact conn [person2])
      id-entity (-> result :tempids vals first)]

  (pprint id-entity)

  ;update patrimony
  (pprint @(d/transact conn [[:db/add id-entity :person/patrimony 0.1M]]))

  ;remove
  (pprint @(d/transact conn [[:db/retract id-entity :person/profession "Baggins"]])))