package ec.erickmedina.paging_test.remote.client

import android.content.Context
import ec.erickmedina.paging_test.exceptions.NoConnectivityException
import ec.erickmedina.paging_test.util.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtil.isOnline(context)) {
            throw NoConnectivityException("No internet connection detected.")
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}
