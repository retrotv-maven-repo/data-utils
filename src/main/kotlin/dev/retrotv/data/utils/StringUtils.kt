@file:JvmName("StringUtils")
package dev.retrotv.data.utils

fun addNewLine(value: String): String = value + "\n"
fun removeNewLine(value: String): String = value.replace("\r", "").replace("\n", "")