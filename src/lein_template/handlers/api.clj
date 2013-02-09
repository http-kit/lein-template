(ns lein-template.handlers.api
  )

(defn get-time [req]
  {:time (System/currentTimeMillis)
   :req req})
