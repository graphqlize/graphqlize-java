(ns graphqlize.java-interop
  (:require [clojure.data.json :as json]
            [com.walmartlabs.lacinia :as lacinia]
            [inflections.core :as inf]
            [graphqlize.lacinia.core :as gql-lacinia])
  (:import [org.graphqlize.java GraphQLResolver]
           [javax.sql DataSource]))

(defn initialize [^DataSource db-spec]
  (let [lacinia-schema (gql-lacinia/schema db-spec)]
    (reify GraphQLResolver
      (resolve [this query]
        (.resolve this query nil))
      (resolve [_ query variables]
        (let [vs (inf/transform-keys (into {} variables) keyword)]
          (json/write-str (lacinia/execute lacinia-schema query vs nil)))))))

(comment
  (import '[org.postgresql.ds PGSimpleDataSource]
          '[org.graphqlize.java GraphQLizeResolver])
  (def ds (doto (PGSimpleDataSource.)
            (.setServerName "localhost")
            (.setDatabaseName "sakila")
            (.setUser "postgres")
            (.setPassword "postgress")))
  (def resolver (GraphQLizeResolver. ds))
  (.resolve resolver "query { actorByActorId(actorId : 1) { firstName } }"))