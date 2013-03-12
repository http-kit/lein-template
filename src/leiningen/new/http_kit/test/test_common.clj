(ns {{sanitized-ns}}.test-common
  (:use [{{sanitized-ns}}.routes :only [server-routes]]
        [clojure.data.json :only [read-str]]))

(def test-app server-routes)

(defn read-json [str] (read-str str :key-fn keyword))
