package org.graphqlize.java;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import javax.sql.DataSource;

public class GraphQLizeResolver implements GraphQLExecutor {
  static {
    IFn require = Clojure.var("clojure.core", "require");
    require.invoke(Clojure.read("graphqlize.java-interop"));
  }
  private static final IFn initializeGraphQLizeResolver = Clojure.var("graphqlize.java-interop", "initialize");
  private final GraphQLExecutor executor;

  public GraphQLizeResolver(DataSource dataSource) {
    executor = (GraphQLExecutor) initializeGraphQLizeResolver.invoke(dataSource);
  }

  @Override
  public String execute(String query) {
    return executor.execute(query);
  }

}