package com.prongbang.backendcram.challenge.model

class ChallengeRequest {
    data class Request(
        val userId: String,
    )

    data class Response(
        val challenge: String,
    )
}