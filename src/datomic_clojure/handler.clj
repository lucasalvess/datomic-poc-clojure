(ns datomic-clojure.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as json]
            [ring.util.response :refer [response]]
            [datomic-clojure.service.person-service :refer :all]))

(defroutes app-routes
           (GET "/api/persons" []
             (response (get-persons)))

           (GET "/api/person/:cpf" [cpf]
             (response (get-person cpf)))

           (POST "/api/person" {:keys [params]}
             (let [{:keys [cpf name gender email birth_date profession
                           patrimony]} params]
               (response (add-person cpf name gender email birth_date profession
                                     patrimony))))

           (PUT "/api/person/:id" [id cpf name gender email birth_date profession
                                   patrimony]
             (response (update-person (Long/parseLong id) cpf name gender email birth_date profession
                                      patrimony)))

           (DELETE "/api/person/:id" [id]
             (response (delete-person (Long/parseLong id))))

           (route/resources "/")
           (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (json/wrap-json-params)
      (json/wrap-json-response)))

