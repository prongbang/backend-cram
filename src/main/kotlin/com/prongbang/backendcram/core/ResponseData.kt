package com.prongbang.backendcram.core

data class ResponseData<T>(
    val code: String,
    val message: String,
    @Transient
    val data: T? = null,
)