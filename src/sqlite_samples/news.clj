(ns sqlite-samples.news
  (:require
    [clojure.java.io :as io]
    [clojure.java.jdbc :refer :all]))

(def testdata
  {:url   "http://example.com",
   :title "SQLite Example",
   :body  "Example using SQLite with Clojure"})


(def db
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "db/database.db"})


(defn create-db
  "create db and table"
  []
  (try (db-do-commands db
                       (create-table-ddl :news
                                         [[:timestamp :datetime :default :current_timestamp]
                                          [:url :text]
                                          [:title :text]
                                          [:body :text]]))
       (catch Exception e
         (println (.getMessage e)))))

(defn print-result-set
  "prints the result set in tabular form"
  [result-set]
  (doseq [row result-set]
    (println row)))


(defn fetch-news
  "execute query and return lazy sequence"
  []
  (query db ["select * from news"]))

(defn insert-testdata [] (insert! db :news testdata))

(defn init
  []
  (create-db)
  (insert-testdata)
  (fetch-news))

(defn drop-all []
  (io/delete-file (:subname db)))