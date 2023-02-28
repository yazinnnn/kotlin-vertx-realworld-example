package io.realworld.model.response

data class ApiResponse<T>(val statusCode: Int = 200, val data: T? = null) {

  constructor(data: T) : this(200, data)

  fun hasData(): Boolean {
    return data != null
  }
}
