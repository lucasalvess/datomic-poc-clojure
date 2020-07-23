(ns datomic-clojure.examples.back-to-the-future
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [datomic-clojure.model.person-model :as model]
            [datomic-clojure.db :as db]
            [datomic-clojure.repository.person-repository :as repository]))

;define an connection
(def conn (db/open-connect))
(db/create-schema conn)

;transact a person
(let [person1
      (model/create-person "111-111-222-11", "Martin Seamus McFly", "M",
                                  "heymacfly@1968.com", (new java.util.Date),
                                  "Studant", 2000.00M)]
  (pprint @(d/transact conn [person1])))

;received the database state (SNAPSHOT)
(def db-past (d/db conn))

(let [person2
      (model/create-person "222-111-222-11", "Emmett Brown", "M",
                           "docbrown@1985.com", (new java.util.Date),
                           "Scientist", 2000.00M)]
  (pprint @(d/transact conn [person2])))

;received the database state (SNAPSHOT)
(def db-present (d/db conn))


;print cont of persons in different moments
(pprint (count (repository/find-all-persons db-past)))    ;then return 1

(pprint (count (repository/find-all-persons db-present)))    ;then return 2

;with the instant in the time
(pprint (count (repository/find-all-persons (d/as-of (d/db conn) #inst "2020-07-23T00:08:20.524"))))

;how my bank was in 2015
(pprint (count (repository/find-all-persons (d/as-of (d/db conn) #inst "2015-10-21"))))

(db/drop-database)
