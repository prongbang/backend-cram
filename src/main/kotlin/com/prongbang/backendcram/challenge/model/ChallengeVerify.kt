package com.prongbang.backendcram.challenge.model

class ChallengeVerify {
    data class Request(
        val userId: String,
        val challenge: String,
        val signature: String,
        val nonce: String,
    )
}