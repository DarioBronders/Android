package com.hogent.android.util

import retrofit2.Response
import timber.log.Timber

class TimberUtils {
    //had ik gebruikt om login async te maken en wat te debuggen, maar heb de response weggedaan
    companion object{
        fun logRequest(response: Response<*>) {
            val stackTrace = Thread.currentThread().stackTrace
            val callingClassName = stackTrace[4].className
            Timber.i(String.format(
                "Request received @$callingClassName%n" +
                "      -Response has status code: %d%n" +
                "      -Response body: %s",
                response.code(), response.body().toString()));
        }

    }
}