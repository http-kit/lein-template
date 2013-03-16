(ns leiningen.new.http-kit
  "A Leiningen template for new Clojure web project"
  (:use [leiningen.new.templates :only [->files name-to-path
                                        sanitize-ns renderer year]]
        [clojure.java.shell :only [sh]])
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def ^{:const true} project-version "1.0.0-SNAPSHOT")

(def render (renderer "http-kit"))

(defn binary [file]
  (io/input-stream (io/resource(str/join "/" ["leiningen" "new" "http_kit" file]))))

;; main template entry point
(defn http-kit
  "A Leiningen template for new Clojure web project"
  [^String short-name & features]
  (let [fq-name short-name
        data {:name            short-name
              :fq-name         fq-name
              :project-version project-version
              :clojure-version "1.5.1"
              :fs-path         (name-to-path fq-name)
              :sanitized-ns    (sanitize-ns fq-name)
              :year            (year)}]
    (->files data
             ["README.md"                                  (render "README.md" data)]
             ["project.clj"                                (render "project.clj" data)]
             [".travis.yml"                                (render "travis.yml" {})]
             ["src/logback.xml"                            (render "logback.xml" data)]

             ["src/{{fs-path}}/config.clj"                 (render "config.clj" data)]
             ["src/{{fs-path}}/main.clj"                   (render "main.clj" data)]
             ["src/{{fs-path}}/routes.clj"                 (render "routes.clj" data)]
             ["src/{{fs-path}}/tmpls.clj"                  (render "tmpls.clj" data)]
             ["src/{{fs-path}}/middleware.clj"             (render "middleware.clj" data)]
             ["src/{{fs-path}}/handlers/api.clj"           (render "handlers/api.clj" data)]
             ["src/{{fs-path}}/handlers/app.clj"           (render "handlers/app.clj" data)]

             ["public/css/style.css"                      (render "public/css/style.css" data)]
             ["public/css/bootstrap-responsive.css"       (render "public/css/bootstrap-responsive.css" data)]
             ["public/css/bootstrap.css"                  (render "public/css/bootstrap.css" data)]
             ["public/img/glyphicons-halflings-white.png" (binary "public/img/glyphicons-halflings-white.png")]
             ["public/img/glyphicons-halflings.png"       (binary "public/img/glyphicons-halflings.png")]
             ["public/js/lib/bootstrap.js"                (binary "public/js/lib/bootstrap.js")]
             ["public/js/lib/jquery-1.9.1.js"             (binary "public/js/lib/jquery-1.9.1.js")]

             ["scripts/run"                                (render "scripts/run" data)]

             ["src/templates/landing.tpl"                  (binary "templates/landing.tpl")]
             ["src/templates/partials/header.tpl"          (binary "templates/partials/header.tpl")]

             ["test/{{fs-path}}/test_common.clj"           (render "test/test_common.clj" data)]
             ["test/{{fs-path}}/app_test.clj"              (render "test/app_test.clj" data)]
             ["test/logback-test.xml"                      (render "test/logback_test.xml" data)])
    (sh "chmod" "+x" (str short-name "/scripts/run"))
    (println "have fun")))