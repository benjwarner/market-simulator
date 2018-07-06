package org.tools4j.fixgrep.help

import org.tools4j.fixgrep.texteffect.Ansi16BackgroundColor
import org.tools4j.fixgrep.texteffect.Ansi16ForegroundColor
import org.tools4j.fixgrep.texteffect.CompositeTextEffect
import org.tools4j.fixgrep.texteffect.HtmlOnlyTextEffect
import org.tools4j.fixgrep.texteffect.MiscTextEffect

/**
 * User: ben
 * Date: 9/05/2018
 * Time: 6:43 AM
 */
class Color16HtmlDemo(val docWriterFactory: DocWriterFactory) {
    fun toFormattedText(): String {
        val writer = docWriterFactory.createNew()
        if(!writer.isHtml()) return ""
        writer.writeLn("</br>If your console supports 16 colors, this demo should show a table like the one below:")
        writer.writeHeading(2, "Foreground colors:")
        writer.startSection(CompositeTextEffect(listOf(MiscTextEffect.Console, HtmlOnlyTextEffect("color16-div"))))
        writer.writeLn(Ansi16ForegroundColor.listAsHtml())
        writer.endSection()
        writer.writeHeading(2, "Background colors:")
        writer.startSection(CompositeTextEffect(listOf(MiscTextEffect.Console, HtmlOnlyTextEffect("color16-div"))))
        writer.writeLn(Ansi16BackgroundColor.listAsHtml())
        writer.endSection()
        return writer.toString()
    }
}