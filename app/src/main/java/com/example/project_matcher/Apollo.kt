import android.content.Context
import android.content.SharedPreferences
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

private var instance: ApolloClient? = null

fun apolloClient(context: Context): ApolloClient {
    if (instance != null) {
        return instance!!
    }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor(context))
        .build()
    instance = ApolloClient.Builder()
        .serverUrl("https://api.github.com/graphql")
        .okHttpClient(okHttpClient)
        .build()
    return instance!!
}

private class AuthorizationInterceptor(val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            "shared_prefs", Context.MODE_PRIVATE)
        val token = sharedPref.getString("user_token", null)
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token" ?: "")
            .build()
        return chain.proceed(request)
    }
}
