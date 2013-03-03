(ns lein-template.routes
  (:use [compojure.core :only [defroutes GET POST DELETE ANY context]]
        (ring.middleware [keyword-params :only [wrap-keyword-params]]
                         [params :only [wrap-params]]
                         [session :only [wrap-session]])
        [lein-template.middleware :only [wrap-failsafe wrap-request-logging-in-dev
                                         wrap-reload-in-dev JGET JPUT JPOST JDELETE]])
  (:require [lein-template.handlers.app :as app]
            [lein-template.handlers.api :as api]
            [compojure.route :as route]))

;; define mapping here
(defroutes server-routes*
  (GET "/" [] app/show-landing)
  (context "/api" []
           ;; JGET returns json encoding of the response
           (JGET "/time" [] api/get-time))
  ;; static files under ./public folder, prefix /static
  ;; like /static/css/style.css
  (route/files "/static")
  ;; 404, modify for a better 404 page
  (route/not-found "<p>Page not found.</p>" ))

(def server-routes (-> server-routes*
                       wrap-session
                       wrap-keyword-params
                       wrap-params
                       wrap-request-logging-in-dev
                       wrap-reload-in-dev
                       wrap-failsafe))