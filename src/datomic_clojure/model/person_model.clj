(ns datomic-clojure.model.person-model)

(defn create-person [cpf name gender email birth_date profession
                    patrimony]
  {
   :person/cpf cpf
   :person/name name
   :person/gender gender
   :person/email email
   :person/birth_date birth_date
   :person/profession profession
   :person/patrimony patrimony
   })
