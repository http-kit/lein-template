(ns lein-template.app-test
  (:use lein-template.test-common
        clojure.test))

(deftest test-landing-page
  (let [resp (test-app {:request-method :get
                        :uri "/"})]
    (is (= 200 (:status resp)))))

(deftest test-landing-page
  (let [resp (test-app {:request-method :get
                        :uri "/api/time"})]
    (is (= 200 (:status resp)))
    (is (-> resp :body read-json :time))))