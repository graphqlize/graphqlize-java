package org.graphqlize.java;

import java.util.Map;

public interface GraphQLResolver {
  String resolve(String query);

  String resolve(String query, Map<String, Object> variables);
}