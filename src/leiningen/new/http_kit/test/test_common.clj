(ns {{sanitized-ns}}.test-common
  (:use [{{sanitized-ns}}.routes :only [app]]
        [clojure.data.json :only [read-str]]))

(def test-app (app))

(defn read-json [str] (read-str str :key-fn keyword))
