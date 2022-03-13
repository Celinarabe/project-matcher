import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://api.github.com/graphql")
    .build()
