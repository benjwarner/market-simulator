package org.tools4j.fixgrep.texteffect

import java.util.stream.Collectors

/**
 * User: ben
 * Date: 5/04/2018
 * Time: 6:55 AM
 */
class CompositeTextEffect(val textEffects: List<TextEffect>): TextEffect {
    override val prettyName: String by lazy {
        val sb = StringBuilder()
        for (textEffect in textEffects) {
            sb.append(textEffect.prettyName)
        }
        sb.toString()
    }

    override val ansiCode: String by lazy {
        textEffects.stream().map{it.ansiCode}.collect(Collectors.toList()).joinToString("")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CompositeTextEffect) return false

        if (textEffects != other.textEffects) return false

        return true
    }

    override fun hashCode(): Int {
        return textEffects.hashCode()
    }

    override fun toString(): String {
        return "CompositeTextEffect(textEffects=$textEffects)"
    }
}