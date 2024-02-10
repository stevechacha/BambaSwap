package bambaswap.com.bambaswap.api

class ApiException(val statusCode: Int = 0, val statusMessage: String) : Throwable(statusMessage)