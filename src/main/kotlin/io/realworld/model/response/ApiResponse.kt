package io.realworld.model.response

data class ApiResponse<T>(val statusCode: Int = 200, val data: T? = null) {


  fun hasData(): Boolean {
    return data != null
  }
}
