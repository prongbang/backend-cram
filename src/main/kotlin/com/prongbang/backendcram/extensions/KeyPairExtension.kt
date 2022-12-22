package com.prongbang.backendcram.extensions

import org.bouncycastle.util.encoders.Base64
import java.security.PrivateKey
import java.security.PublicKey

fun PublicKey.toBase64() = String(encoded.encodeBase64())

fun PrivateKey.toBase64() = String(encoded.encodeBase64())

fun PublicKey.toPEM() = "-----BEGIN PUBLIC KEY-----\n${this.toBase64()}\n-----END PUBLIC KEY-----"

fun PrivateKey.toPEM() =
    "-----BEGIN PRIVATE KEY-----\n${this.toBase64()}\n-----END PRIVATE KEY-----"

fun ByteArray.encodeBase64(): ByteArray = Base64.encode(this)

fun String.decodeBase64(): ByteArray = Base64.decode(this)