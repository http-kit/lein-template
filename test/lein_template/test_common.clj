(ns lein-template.test-common
  (:use [lein-template.core :only [server-routes]]
        [clojure.data.json :only [read-str]]))

(def test-app (server-routes))

(defn read-json [str] (read-str str :key-fn keyword))
