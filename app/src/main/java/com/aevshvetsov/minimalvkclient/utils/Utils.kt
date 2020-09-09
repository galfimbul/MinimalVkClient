package com.aevshvetsov.minimalvkclient.utils

import android.content.res.Resources

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trimStart()?.trim()?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(parts.size - 1)
        if (firstName.isNullOrBlank()) firstName = null
        if (lastName.isNullOrBlank() || firstName.equals(lastName)) lastName = null
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {

        val string = payload.trim()
        val result = StringBuilder()
        val translit = mapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
        )
        if (payload.trim().isNullOrEmpty()) return ""
        else {
            for (symbol in string.indices) {
                if (string[symbol].isWhitespace()) {
                    result.append(divider)
                } else
                    if ("${string[symbol]}" in translit) {
                        result.append(translit["${string[symbol]}"])
                    } else if ("${string[symbol].toLowerCase()}" in translit) {
                        result.append(translit.get("${string[symbol].toLowerCase()}")?.capitalize())
                    } else if (translit.containsValue("${string[symbol].toLowerCase()}")) {
                        result.append("${string[symbol]}")
                    } else {
                        result.append("${string[symbol]}")
                    }
            }

            return result.toString()
        }

    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val result: String?
        if (firstName.isNullOrBlank() && lastName.isNullOrBlank()) {
            result = null
        } else if (!firstName.isNullOrBlank() && lastName.isNullOrBlank()) {
            result = "${firstName[0].toUpperCase()}"
        } else if (firstName.isNullOrBlank() && !lastName.isNullOrBlank()) {
            result = "${lastName[0].toUpperCase()}"
        } else result = "${firstName?.get(0)?.toUpperCase()}${lastName?.get(0)?.toUpperCase()}"


        return result

    }

    fun dpToPx(dp: Int): Float {
        return dp.toFloat() * Resources.getSystem().displayMetrics.density
    }

    fun PxToDp(pixels: Int): Int {
        return pixels / Resources.getSystem().displayMetrics.density.toInt()
    }

    fun SpToPx(sp: Int): Int {
        return sp * Resources.getSystem().displayMetrics.scaledDensity.toInt()
    }
}