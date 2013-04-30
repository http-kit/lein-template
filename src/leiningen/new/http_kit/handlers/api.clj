(ns {{sanitized-ns}}.handlers.api
  )

(defn get-time [req]
  {:time (System/currentTimeMillis)
   :req (merge req {:async-channel nil})})
