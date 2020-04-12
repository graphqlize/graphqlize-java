(defproject org.graphqlize/graphqlize-java "0.1.0-alpha8"
  :description "Java wrapper for graphqlize library"
  :url "https://github.com/graphqlize/graphqlize-java"
  :license {:name "EPL-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.graphqlize/graphqlize "0.1.0-alpha9"]]
  :java-source-paths ["java"]
  :profiles {:test {:dependencies [[org.postgresql/postgresql "42.2.10"]]}})
