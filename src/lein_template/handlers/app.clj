(ns lein-template.handlers.app
  (:require [lein-template.tmpls :as tmpl]))

(defn show-landing [req]
  (tmpl/landing))