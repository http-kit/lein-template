(defproject {{sanitized-ns}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main {{sanitized-ns}}.main
  :aot [{{sanitized-ns}}.main]
  :uberjar-name "{{sanitized-ns}}-standalone.jar"
  ;; :plugins [[lein-swank "1.4.4"]]
  :plugins [[lein-cljfmt "0.3.0"]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.2.6"]
                 [compojure "1.4.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-devel "1.4.0"]

                 [org.clojure/data.json "0.2.6"]

                 [http-kit "2.1.21-alpha2"]

                 [http-kit/dbcp "0.1.0"] ;; database access

                 [mysql/mysql-connector-java "5.1.21"] ;; mysql jdbc driver

                 ;; [org.fressian/fressian "0.6.3"]

                 ;; for serialization clojure object to bytes
                 ;; [com.taoensso/nippy "1.1.0"]

                 ;; Redis client & message queue
                 ;; [com.taoensso/carmine "1.5.0"]

                 ;; logging,  another option [com.taoensso/timbre "1.5.2"]
                 [org.clojure/tools.logging "0.2.6"]
                 [ch.qos.logback/logback-classic "1.0.1"]
                 ;; template
                 [me.shenfeng/mustache "1.1"]])
