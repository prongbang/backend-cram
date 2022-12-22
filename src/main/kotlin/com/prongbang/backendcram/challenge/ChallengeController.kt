package com.prongbang.backendcram.challenge

import com.prongbang.backendcram.challenge.model.ChallengeRegistration
import com.prongbang.backendcram.challenge.model.ChallengeRequest
import com.prongbang.backendcram.challenge.model.ChallengeVerify
import com.prongbang.backendcram.core.ResponseData
import com.prongbang.backendcram.core.ResponseToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class ChallengeController {

    @Autowired
    lateinit var challengeService: ChallengeService

    @PostMapping("/challenge:registration")
    fun registration(@RequestBody body: ChallengeRegistration): ResponseEntity<ResponseData<Unit>> {

        challengeService.registration(body)

        return ResponseEntity.ok(
            ResponseData(
                code = "${HttpStatus.OK.value()}",
                message = "${HttpStatus.OK.series()}"
            )
        )
    }

    @PostMapping("/challenge:request")
    fun request(@RequestBody body: ChallengeRequest.Request): ResponseEntity<ResponseData<ChallengeRequest.Response>> {

        val challenge = challengeService.request(body)

        return ResponseEntity.ok(
            ResponseData(
                code = "${HttpStatus.OK.value()}",
                message = "${HttpStatus.OK.series()}",
                data = ChallengeRequest.Response(challenge = challenge)
            )
        )
    }

    @PostMapping("/challenge:verify")
    fun verify(@RequestBody body: ChallengeVerify.Request): ResponseEntity<ResponseData<ResponseToken>> {

        val result: Boolean = challengeService.verify(body)

        if (result) {
            return ResponseEntity.ok(
                ResponseData(
                    code = "${HttpStatus.OK.value()}",
                    message = "${HttpStatus.OK.series()}",
                    data = ResponseToken(
                        token = UUID.randomUUID().toString()
                    )
                )
            )
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseData(
                code = "${HttpStatus.BAD_REQUEST.value()}",
                message = "Verify error",
            )
        )
    }

}