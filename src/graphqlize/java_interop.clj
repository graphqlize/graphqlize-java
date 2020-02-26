(ns graphqlize.java-interop
  (:require [honeyeql.db :as heql-db]
            [clojure.data.json :as json]
            [com.walmartlabs.lacinia :as lacinia]
            [graphqlize.lacinia.core :as gql-lacinia])
  (:import [org.graphqlize.java GraphQLExecutor]
           [javax.sql DataSource]))

(defn initialize [^DataSource db-spec]
  (let [db-adapter     (honeyeql.db/initialize db-spec)
        lacinia-schema (gql-lacinia/schema db-adapter)]
    (reify GraphQLExecutor
      (execute [_ query]
        (json/write-str (lacinia/execute lacinia-schema query nil nil))))))

(comment
  (import '[org.postgresql.ds PGSimpleDataSource]
          '[org.graphqlize.java GraphQLizeResolver])
  (def ds (doto (PGSimpleDataSource.)
            (.setServerName "localhost")
            (.setDatabaseName "sakila")
            (.setUser "postgres")
            (.setPassword "postgress")))
  (.execute (initialize ds) "query { actorByActorId(actorId : 1) { firstName } }")
  (def resolver (GraphQLizeResolver. ds)))