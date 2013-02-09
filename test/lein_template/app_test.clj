(ns lein-template.app-test
  (:use [lein-template.test-common :only [test-app]]
        clojure.test))

(deftest test-landing-page
  (let [resp (test-app {:request-method :get
                        :uri "/"})]
    (is (= 200 (:status resp)))))