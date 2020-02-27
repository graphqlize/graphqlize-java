package org.graphqlize.java;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import javax.sql.DataSource;

public class GraphQLizeResolver implements GraphQLResolver {
  static {
    IFn require = Clojure.var("clojure.core", "require");
    require.invoke(Clojure.read("graphqlize.java-interop"));
  }
  private static final IFn initializeGraphQLizeResolver = Clojure.var("graphqlize.java-interop", "initialize");
  private final GraphQLResolver resolver;

  public GraphQLizeResolver(DataSource dataSource) {
    resolver = (GraphQLResolver) initializeGraphQLizeResolver.invoke(dataSource);
  }

  @Override
  public String resolve(String query) {
    return resolver.resolve(query);
  }

}