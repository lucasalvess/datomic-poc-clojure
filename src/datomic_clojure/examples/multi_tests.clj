(ns datomic-clojure.examples.multi-tests
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [datomic-clojure.db :as db]
            [datomic-clojure.examples.predicates :as predicate]
            [datomic-clojure.repository.person-repository :as repository]
            [datomic-clojure.model.person-model :as model]))

;define an connection
(def conn (db/open-connect))
(db/create-schema conn)

;transact persons
(let [person1
      (model/create-person "111.111.111-11", "Gandalf the Grey", "M",
                           "gandof@middleearth.com", (new java.util.Date), "Mage", 10000.00M)
      person2
      (model/create-person "222.222.222-22", "Frodo Baggins", "M",
                           "frodo@middleearth.com", (new java.util.Date), "Baggins", 700.00M)
      person3
      (model/create-person "333.333.333-33", "Legolas Greenleaf", "M",
                           "legolas@valfenda.com", (new java.util.Date), "Messenger", 700.00M)]
  (pprint @(d/transact conn [person1,person2,person3])))

;query test examples
(pprint (predicate/find-all-persons-predicate (d/db conn) 1000.00M))
(pprint (predicate/find-all-persons-predicate-and-action-plan (d/db conn) 1000.00M "Messenger"))
(pprint (repository/find-all-persons (d/db conn)))
(pprint (repository/find-by-cpf (d/db conn) "333.333.333-33"))

(db/drop-database)
