(ns datomic-clojure.examples.predicates
  (:require [datomic.api :as d]
            [datomic-clojure.db :as db]
            [datomic-clojure.model.person-model :as model]))
;  Predicates and action plan examples
;-------------------------------------------------
;define an connection
(def conn (db/open-connect))
(db/create-schema conn)

;query's with condition (predicate)
;return person with patrimony < max-patrimony
(defn find-all-persons-predicate [db max-patrimony]
  (d/q '[:find (pull ?entity [*])
         :in $ ?max
         :where [?entity :person/name]
         [?entity :person/patrimony ?patrimony]
         [(< ?patrimony max)]] db max-patrimony))

;query with action plan (the order of the conditions matter)
;return person with patrimony < max-patrimony and profession = "Messenger"
(defn find-all-persons-predicate-and-action-plan [db max-patrimony occupation]
  (d/q '[:find (pull ?entity [*])
         :in $ ?max ?occupation
         :where [?entity :person/name]
         [?entity :person/patrimony ?patrimony]
         [?entity :person/profession ?profession]
         [(= ?profession ?occupation)]
         [(< ?patrimony ?max)]] db max-patrimony occupation))