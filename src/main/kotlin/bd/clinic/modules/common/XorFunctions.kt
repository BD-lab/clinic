package bd.clinic.modules.common

import java.util.*
import kotlin.experimental.xor

fun encode(input: String): String {
    return Base64.getEncoder().encodeToString(input.toByteArray().map { it.xor(56) }.toByteArray())
}

fun decode(input: String): String {
    return String(Base64.getDecoder().decode(input).map { it.xor(56) }.toByteArray())
}
