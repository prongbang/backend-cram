package com.prongbang.backendcram.core.keypair

import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.util.encoders.Base64
import java.security.KeyFactory
import java.security.PublicKey
import java.security.Security
import java.security.spec.X509EncodedKeySpec

object KeyPairManager {

    init {
        Security.addProvider(BouncyCastleProvider())
    }

    fun toPublicKey(publicKey: String): PublicKey {
        val pk = publicKey
            .replace("\\-*BEGIN.*KEY\\-*","")
            .replace("\\-*END.*KEY\\-*", "")
            .replace("\r", "")
            .replace("\n", "")
        val pkByteArray = Base64.decode(pk)
        return toPublicKey(pkByteArray)
    }

    fun toPublicKey(pkb: ByteArray): PublicKey {
        val fact = KeyFactory.getInstance("EC")
        return fact.generatePublic(X509EncodedKeySpec(pkb))
    }
}