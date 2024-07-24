package com.example.devtestapp.linksPage.repository

import android.content.Context
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonObjectRequest
import com.example.devtestapp.MySingleton
import com.example.devtestapp.components.API_KEY.api_key
import com.example.devtestapp.linksPage.model.ApiResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONObject
import java.nio.charset.Charset
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


suspend fun apiResponse(context: Context): ApiResponse{
    val url = "https://api.inopenapp.com/api/v1/dashboardNew"
    val token = api_key //You need to add your own API key here
    return suspendCancellableCoroutine { continuation ->

        val myJsonRequest: JsonObjectRequest = object : JsonObjectRequest(
            Method.GET,
            url,
            null,
            Response.Listener { response ->
                Log.d("API Response", response.toString())
                try {
                    val apiResponse = Gson().fromJson(response.toString(), ApiResponse::class.java)
                    continuation.resume(apiResponse)
                } catch (e: JsonSyntaxException) {
                    continuation.resumeWithException(e)
                }
//                continuation.resume(Unit)
            },
            Response.ErrorListener {
                if (it.message != null) {
                    val errorMessage = "Error: ${it.message}"
                    continuation.resumeWithException(Exception(errorMessage))
                }
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()

                headers["Authorization"] = "Bearer $token"
                return headers
            }


            override fun getBody(): ByteArray {
                val params = HashMap<String, String>()
                return JSONObject(params as Map<*, *>?).toString().toByteArray(Charset.defaultCharset())
            }

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun parseNetworkResponse(response: NetworkResponse): Response<JSONObject> {
                return if (response.data.isEmpty()) {
                    val responseData =
                        "{}".toByteArray(Charset.defaultCharset())  // Use default Charset
                    val modifiedResponse = NetworkResponse(
                        response.statusCode,
                        responseData,
                        response.headers,
                        response.notModified
                    )
                    super.parseNetworkResponse(modifiedResponse)
                } else {
                    val jsonString = response.data?.toString(Charsets.UTF_8)
                    val jsonObject = jsonString?.let { JSONObject(it) }
                    return Response.success(
                        jsonObject,
                        HttpHeaderParser.parseCacheHeaders(response)
                    )
                }
            }

        }
        // Set a custom retry policy with timeout and retry attempts
        val retryPolicy = DefaultRetryPolicy(
            1000,
            3,
            1.0f
        )

        myJsonRequest.retryPolicy = retryPolicy
        MySingleton.getInstance(context).addToRequestQueue(myJsonRequest)

    }
}

