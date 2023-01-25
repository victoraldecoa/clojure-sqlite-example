(defproject clojure-sqlite-example "1.0.0"
  :description "A simple example of using SQLite with Clojure"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/java.jdbc "0.7.9"]
                 [org.xerial/sqlite-jdbc "3.36.0.3"]]
  :main ^:skip-aot clojure-sqlite-example.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
