(ns lein-template.middleware
  (:use [compojure.core :only [GET POST DELETE PUT]]
        [clojure.tools.logging :only [debug error info]]
        [lein-template.config :as conf]
        [clojure.data.json :only [write-str]]))

(defn wrap-failsafe [handler]
  (fn [req]
    (try (handler req)
         (catch Exception e
           (error e "error handling request" req)
           {:status 500 :body "Sorry, an error occured."}))))

(defn wrap-request-logging-in-dev [handler]
  (if (= (conf/cfg :profile) :dev)
    (fn [{:keys [request-method ^String uri] :as req}]
      (let [start (System/currentTimeMillis)
            resp (handler req)
            finish (System/currentTimeMillis)]
        (info (name request-method) (:status resp)
              (if-let [qs (:query-string req)]
                (str uri "?" qs) uri)
              (str (- finish start) "ms"))
        resp))
    handler))

(defn wrap-reload-in-dev [handler]
  (if (= (conf/cfg :profile) :dev)
    (fn [req]
      (require :reload 'lein-template.tmpls)
      (handler req))
    handler))

(defn wrap-json [handler]
  (fn [req]
    (let [resp (handler req)
          json-resp (if (contains? resp :body)
                      (update-in resp [:body] write-str)
                      {:body (write-str resp)})]
      (update-in (merge {:status 200} json-resp)
                 [:headers] merge {"Content-Type"
                                   "application/json; charset=utf-8"}))))

(defmacro JPOST [path args handler]
  `(POST ~path ~args (wrap-json ~handler)))

(defmacro JPUT [path args handler]
  `(PUT ~path ~args (wrap-json ~handler)))

(defmacro JGET [path args handler]
  `(GET ~path ~args (wrap-json ~handler)))

(defmacro JDELETE [path args handler]
  `(DELETE ~path ~args (wrap-json ~handler)))