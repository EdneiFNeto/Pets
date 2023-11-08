package com.pets.helpers

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String? = null
) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> returnResponse(response: Response<T>): Resource<T?> {

            return if (!response.isSuccessful) {
                val errorBody = response.errorBody()
                Log.e(TAG, "errorBody $errorBody")

                val errors = if (response.code() in 500..599) {
                    if (errorBody == null) {
                        FAIL_CONNECTION_SERVER
                    } else {
                        converterError(errorBody).error ?: FAIL_CONNECTION_SERVER
                    }
                } else if (errorBody != null) {
                    converterError(errorBody).error ?: FAIL_CONNECTION_API
                } else {
                    FAIL_CONNECTION_API
                }

                Log.e(TAG, "errors $errors")
                error(data = null, msg = errors)
            } else {
                val body = response.body()
                Log.i(TAG, "body $body")
                success(data = body)
            }
        }

        private fun converterError(responseBody: ResponseBody) =
            Gson().fromJson(responseBody.getJson().toString(), MessageError::class.java)

        private fun ResponseBody.getJson(): JSONObject {
            val parse = JsonParser.parseString(this.string()).asJsonObject
            return JSONObject(parse.toString())
        }

        private const val TAG = "ResoucesLog"
        private const val FAIL_CONNECTION_API = "Fail connection api"
        private const val FAIL_CONNECTION_SERVER = "Fail connection server"
    }
}

class MessageError(val error: String?)


enum class Status {
    SUCCESS,
    ERROR
}