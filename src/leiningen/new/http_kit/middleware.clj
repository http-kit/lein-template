(ns {{sanitized-ns}}.middleware
    (:use [compojure.core :only [GET POST DELETE PUT PATCH]]
          [clojure.tools.logging :only [debug error info]]
          [{{sanitized-ns}}.config :as conf]
          [clojure.data.json :only [write-str]]))

(defn wrap-failsafe [handler]
  (fn [req]
    (try (handler req)
         (catch Exception e
           (error e "error handling request" req)
           ;; provide a better page for 500 here
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
      (require :reload '{{sanitized-ns}}.tmpls) ; reload templates
      (handler req))
    handler))

(defn json-response [resp]
  (let [json-resp (if (and (map? resp) (contains? resp :body))
                    (update-in resp [:body] write-str)
                    {:body (write-str resp)})]
    (update-in (merge {:status 200} json-resp)
               [:headers] merge {"Content-Type"
                                 "application/json; charset=utf-8"})))

(defn wrap-json [handler] (fn [req] (json-response (handler req))))

(defmacro JPOST [path args handler]
  `(POST ~path ~args (wrap-json ~handler)))

(defmacro JPUT [path args handler]
  `(PUT ~path ~args (wrap-json ~handler)))

(defmacro JPATCH [path args handler]
  `(PATCH ~path ~args (wrap-json ~handler)))

(defmacro JGET [path args handler]
  `(GET ~path ~args (wrap-json ~handler)))

(defmacro JDELETE [path args handler]
  `(DELETE ~path ~args (wrap-json ~handler)))
