package com.prongbang.backendcram.challenge

import com.prongbang.backendcram.challenge.model.ChallengeRegistration
import com.prongbang.backendcram.challenge.model.ChallengeRequest
import com.prongbang.backendcram.challenge.model.ChallengeVerify
import com.prongbang.backendcram.core.keypair.KeyPairManager
import org.bouncycastle.util.encoders.Base64
import org.springframework.stereotype.Service
import java.security.Signature
import java.util.*

@Service
class ChallengeService {

    companion object {
        private val registrationMap = hashMapOf<String, String>()
        private val challengeMap = hashMapOf<String, String>()
    }

    fun registration(registration: ChallengeRegistration): Boolean {
        registrationMap[registration.userId] = registration.pk

        return true
    }

    fun request(request: ChallengeRequest.Request): String {
        val challenge = UUID.randomUUID().toString()

        challengeMap[request.userId] = challenge

        return challenge
    }

    fun verify(request: ChallengeVerify.Request): Boolean {
        val pk = registrationMap[request.userId]!!
        val publicKey = KeyPairManager.toPublicKey(pk)

        val sign = Signature.getInstance("SHA256withECDSA")
        val textToSign = request.challenge + request.nonce
        sign.initVerify(publicKey)
        sign.update(textToSign.toByteArray(Charsets.UTF_8))

        val signatureByte = Base64.decode(request.signature)
        val verify = sign.verify(signatureByte)
        val matchChallenge = challengeMap[request.userId] == request.challenge

        return verify == matchChallenge
    }

}
