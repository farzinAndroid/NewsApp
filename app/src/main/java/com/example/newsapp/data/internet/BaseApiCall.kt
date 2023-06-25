package com.example.newsapp.data.internet

import com.example.newsapp.data.model.BaseResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseApiCall {

    suspend fun <T> safeApiCall(apiCall:suspend () -> Response<BaseResponseModel<T>>) : NetworkResult<T> =
        withContext(Dispatchers.IO){
            try {

                val response = apiCall()
                if (response.isSuccessful){
                    val body = response.body()
                    body?.let {
                        return@withContext NetworkResult.Success(body.status,body.articles)
                    }
                }
                return@withContext error("code : ${response.code()} , message : ${response.message()}")

            }catch (e:Exception){
                return@withContext error(e.message ?: e.toString())
            }
        }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed : $errorMessage")

}