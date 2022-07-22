package com.test.giphy.network.exception

class GifException(errorCode: String?) :
    Exception("Failed to load photos, errorCode = ${errorCode ?: "UNKNOWN"}!")
