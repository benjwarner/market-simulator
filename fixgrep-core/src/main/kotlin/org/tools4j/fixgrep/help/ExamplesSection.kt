package org.tools4j.fixgrep.help

import org.tools4j.fixgrep.highlights.ExamplesList
import org.tools4j.fixgrep.texteffect.HtmlOnlyTextEffect
import org.tools4j.fixgrep.texteffect.MiscTextEffect
import org.tools4j.fixgrep.utils.Constants.Companion.DOLLAR

/**
 * User: ben
 * Date: 22/05/2018
 * Time: 5:50 PM
 */
class ExamplesSection(val docWriterFactory: DocWriterFactory) {
    fun toFormattedText(): String {
        val lines = listOf(
                "2018-05-30T07:12:34.456 8=FIX.5.0|9=123|35=D|49=ABC_CORP|56=ACME_EXCHANGE|11=C28|55=AUD/USD|54=2|38=1464820|40=2|44=100.026|10=131",
                "2018-05-30T07:12:35.101 8=FIX.5.0|9=234|35=8|49=ACME_EXCHANGE|56=ABC_CORP|37=O26|11=C28|41=C28|55=AUD/USD|54=2|17=O26_1|150=0|39=A|151=1464820|14=0|44=100.02|10=434",
                "2018-05-30T07:22:12.350 8=FIX.5.0|9=345|35=G|49=ABC_CORP|56=ACME_EXCHANGE|41=C28|11=C32|55=AUD/USD|54=2|38=1465320|40=2|44=100.12|10=211",
                "2018-05-30T07:22:13.670 8=FIX.5.0|9=456|35=8|49=ACME_EXCHANGE|56=ABC_CORP|37=O26|11=C32|41=C28|55=AUD/USD|54=2|17=O26_2|150=5|39=0|151=1465320|14=0|44=100.12|10=566",
                "2018-05-30T07:34:34.060 8=FIX.5.0|9=567|35=8|49=ACME_EXCHANGE|56=ABC_CORP|37=O26|11=C32|41=C28|55=AUD/USD|54=2|17=O26_3|150=2|39=0|151=1072490|14=392830|32=392830|44=100.00|58=Executed|10=788")

        val docWriter = docWriterFactory.createNew()
        docWriter
                .writeHeading(1, "EXAMPLES")
                .writeLn("Examples are being applied to the following lines of fix: ")
                .startSection(MiscTextEffect.Console)
        lines.forEach { docWriter.writeLn(it) }
        docWriter.endSection()

        var examplesList = ExamplesList(lines, docWriter)
        docWriter.writeLn()
        examplesList.add("<no arguments>", "This is how fix messages will appear by default by fixgrep.  Notice that the date has been stripped from the front of each message.  See later examples on how to add this date back in.")
        examplesList.add("--exclude-tags 8,9,10", "Exclude tags 8 (BeginString),9 (BodyLength),10 (CheckSum) from showing.  These are repetitive tags which most of the time just add 'noise' to fix messages.  To exclude these from all calls, configure the exclude.tags property in your ~/.fixgrep/application.properties file.")
        examplesList.add("-e 8,9,10", "Same configuration to exclude tags 8,9,10 but using short form option -e")
        examplesList.add("--exclude-messages-of-type 8", "Excludes messages of type 8 (ExecutionReport).")
        examplesList.add("-v 8", "Same exclusion of ExecutionReports using the 'short form' option v")
        examplesList.add("-v D", "Excluding messages of type D (NewOrderSingle)")
        examplesList.add("-v D,8", "Excluding both messages of type D (NewOrderSingle) and of type 8 (ExecutionReport)")
        examplesList.add("-v D -v 8", "Same exclusion, but using repeated options.")
        examplesList.add("--tag-annotations outsideAnnotated", "Set annotations to 'outside' meaning the annotated tag will sit before the rawTag, and the annotated value will sit after the rawValue.  Like this [tagAnnotation]rawTag=rawValue[valueAnnotation], e.g. [Side]54=2[SELL].  This is the default annotation format used by fixgrep, so don't be surprised if the formatted fix did not change.")
        examplesList.add("-a outsideAnnotated", "Same, but using the short form option 'a'")
        examplesList.add("-a ba", "Same, but using abbreviated form 'ba' meaning 'before-after'")
        examplesList.add("-a insideAnnotated", "Inside annotation, meaning the annotated tag will sit after the rawTag, and the annotated value will sit before the rawValue.  Like this rawTag[tagAnnotation]=[valueAnnotation]rawValue, e.g. 54[Side]=[SELL]2")
        examplesList.add("-a ab", "Same as 'insideAnnotated' but specified in abbreviated form 'ab' meaning after-before.")
        examplesList.add("-a b_", "Annotations set to before-none, meaning only Tag annotations will be printed, before the raw tag value.")
        examplesList.add("-a a_", "Annotations set to after-none, meaning only Tag annotations will be printed, after the raw tag value.")
        examplesList.add("-a _b", "Annotations set to none-before, meaning only Tag annotations will be printed, before the raw tag value.")
        examplesList.add("-a _a", "Annotations set to none-after, meaning only Tag annotations will be printed, after the raw tag value.")
        examplesList.add("-a __", "Configuring NO annotations.")
        examplesList.add("-a none", "Another way of configuring NO annotations.")
        examplesList.add("--include-only-messages-of-type D", "Will only print messages of type D (NewOrderSingle).")
        examplesList.add("-m D", "Same inclusion of just NewOrderSingle messages, but option specified in short form 'm'")
        examplesList.add("-m D,8", "Only show NewOrderSingles and ExecutionReports")
        examplesList.add("--output-delimiter :", "Set output delimiter to ':'.  (Default is pipe '|')")
        examplesList.add("-o :", "Same, but using short form option -o")
        examplesList.add("--sort-by-tags 55,11", "For each fix line, show tags 55 then 11 first if they exist, followed by other tags in the order that they originally appeared.")
        examplesList.add("-s 55,11", "Same configuration, but using short form option -s.")
        examplesList.add("-s 55,11,37", "Show tags 55, then 11, then 37 first on each line, followed by other tags in the order that they originally appeared.")
        examplesList.add("--only-include-tags 35", "Only show tag 35.")
        examplesList.add("-t 35", "Same configuration to only show tag 35, but using short form option -t")
        examplesList.add("-t 35,55", "Only show tags 35 and 55")
        examplesList.add("-t 35,11", "Only show tags 35 and 11")
        examplesList.add("--highlights 35", "Highlight tag 35 irrespective of value.")
        examplesList.add("-h 35", "Same highlight but using short form option -h")
        examplesList.add("-h 35=8", "Highlight tag 35 when it's value is 8 (ExecutionReport)")
        examplesList.add("-h 35=8:Line", "Highlight whole lines when tag 35=8 (ExecutionReport)")
        examplesList.add("-h 35=8:Bg3:Line", "Highlight whole lines with background color 3 of the 256 color map, when tag 35=8 (ExecutionReport)")
        examplesList.add("-h 35,55", "Highlight tags 35 and 55.")
        examplesList.add("-h 35:Bg8,55:Bg9", "Highlight tag 35 with a background color of 8.  Highlight tag 55 with a background color of 9.")
        examplesList.add("-h 35=D:Line,55", "When a message has tag 35=D, highlight the whole line.  Also highlight tag 55.")
        examplesList.add("--suppress-bold-tags-and-values", "Don't use bold text effects in the output fix.")
        examplesList.add("-p", "Same configuration but with short form option -p")
        examplesList.end()


        val lines2 = listOf(
                "2018-05-30T07:12:34.456 Thread-11 gbfix001 8=FIX.5.0|9=123|35=D|11=C28|55=AUD/USD|54=2|38=1464820|40=2|44=100.026|10=131",
                "2018-05-30T07:12:35.101 Thread-11 gbfix001 Processing new order single, ClOrderId=11, mid price captured at time 07:12:34.011=100.3",
                "2018-05-30T07:12:35.101 Thread-11 gbfix001 Sending back PendingNew for OrderId=O26 ClOrderId=11",
                "2018-05-30T07:12:35.101 Thread-11 gbfix001 8=FIX.5.0|9=234|35=8|37=O26|11=C28|41=C28|55=AUD/USD|54=2|17=O26_1|150=0|151=1464820|14=0|44=100.02|10=434",
                "2018-05-30T07:22:12.350 Thread-06 gbfix001 8=FIX.5.0|9=345|35=G|41=C28|11=C32|55=AUD/USD|54=2|38=1465320|40=2|44=100.12|10=211",
                "2018-05-30T07:12:35.101 Thread-06 gbfix002 Amend request recieved for OrderId=O26",
                "2018-05-30T07:22:13.670 Thread-01 gbfix002 8=FIX.5.0|9=456|35=8|37=O26|11=C32|41=C28|55=AUD/USD|54=2|17=O26_2|150=5|151=1465320|14=0|44=100.12|10=566",
                "2018-05-30T07:34:34.060 Thread-13 gbfix002 8=FIX.5.0|9=567|35=8|37=O26|11=C32|41=C28|55=AUD/USD|54=2|17=O26_3|150=2|151=1072490|14=392830|32=392830|44=100.00|58=Executed|10=788")

        examplesList = ExamplesList(lines2, docWriter)

        docWriter.writeLn()
                .writeHeading(2, "Parsing log lines")
                .writeLn("So far we have looked at examples of options which just change the way the FIX message is presented.  It is also worth considering how fixgrep parses log files, and how parts of that log line (not just the FIX) can be printed in the fixgrep output.")
                .writeLn("It is common for FIX messages to be printed in the same file as application logs.  It is also common to have other things printed on the same line as the FIX message.  Such as timestamps, thread numbers, etc.")
                .writeLn("Let's consider these log lines as input:")

        docWriter.startSection(MiscTextEffect.Console)
        lines.forEach { docWriter.writeLn(it) }
        docWriter.endSection()

                .writeLn("There are option (-F --output-line-format property:line.format), which can be used to configure the regular expression that is run against every log line.  By default this is:")
                .writeLn("^(\\d{4}-[01]\\d-[0-3]\\d[T\\s][0-2]\\d:[0-5]\\d:[0-5]\\d[\\.,]\\d+)?.*?(\\d+=.*$)", MiscTextEffect.Console)
                .writeLn("...which translates to 'look for an optional date at the start of the line, followed by any characters, followed by one or more digits, followed by an equals sign.")
                .writeLn("Parsing our new input lines outputs this:")

        examplesList.add("<no arguments>", "Parsing new log lines with no arguments.")

        docWriter
                .writeLn("Oh no! fixgrep assumed the line containing '...mid price captured at time 07:12:34.011=100.3' contained a FIX message because of the '11=100.3' text, it then printed out: '[ClOrdID]11=100.3'.  To remedy this, we can use a more specific regex:")

        examplesList.add("--input-line-format ^(\\d{4}-[01]\\d-[0-3]\\d[T\\s][0-2]\\d:[0-5]\\d:[0-5]\\d[\\.,]\\d+)?.*?(8=FIX.*\$)", null)

        docWriter.writeLn("That's better.  Now that log line is not picked up.  Let's assume we do wish to show the date at the start of each line.  We do this by specifying $DOLLAR$1 at the start of the output-line-format.")

        examplesList.add("--output-line-format ${'$'}1:${'$'}{msgFix} --input-line-format ^(\\d{4}-[01]\\d-[0-3]\\d[T\\s][0-2]\\d:[0-5]\\d:[0-5]\\d[\\.,]\\d+)?.*?(8=FIX.*\$)", null)

        docWriter.writeLn("Whilst we're at it, let's just print out the time by slightly modifying the position of the first set of capturing brackets, as the whole date becomes a bit redundant.")

        examplesList.add("--output-line-format ${'$'}1:${'$'}{msgFix} --input-line-format ^\\d{4}-[01]\\d-[0-3]\\d[T\\s]([0-2]\\d:[0-5]\\d:[0-5]\\d[\\.,]\\d+)?.*?(8=FIX.*\$)", null)

        docWriter.writeLn("We can use another pre-defined tag ${DOLLAR}{msgTypeName} to print out not just the messageType, but also the execType if it's an execution report.")

        examplesList.add("--output-line-format ${DOLLAR}1:${DOLLAR}{msgTypeName}:${'$'}{msgFix} --input-line-format ^\\d{4}-[01]\\d-[0-3]\\d[T\\s]([0-2]\\d:[0-5]\\d:[0-5]\\d[\\.,]\\d+)?.*?(8=FIX.*\$)", "Printing out the msgTypeName at the start of the message.")
        examplesList.add("--output-line-format ${DOLLAR}1:${'$'}{msgColor}${DOLLAR}{msgTypeName}${'$'}{colorReset}:${'$'}{msgFix} --input-line-format ^\\d{4}-[01]\\d-[0-3]\\d[T\\s]([0-2]\\d:[0-5]\\d:[0-5]\\d[\\.,]\\d+)?.*?(8=FIX.*\$)", "And add coloring per message type")
        examplesList.add("--exclude-tags 8,9,35,10 --output-line-format ${DOLLAR}1:${'$'}{msgColor}${DOLLAR}{msgTypeName}${'$'}{colorReset}:${'$'}{msgFix} --input-line-format ^\\d{4}-[01]\\d-[0-3]\\d[T\\s]([0-2]\\d:[0-5]\\d:[0-5]\\d[\\.,]\\d+)?.*?(8=FIX.*\$)", "Now let's get rid of some of the tags we just don't care about. (I'm going to hide 35 as well, because we're already printing out the messageTypeName)")

        docWriter.writeLn("This is now looking more presentable.  If I want to have these settings applied each time I run fixgrep, I just need to modify my ~/.fixgrep/application.properties file. Fixgrep can automatically create this file by running:")
        docWriter.writeLn("fixgrep --install", MiscTextEffect.Console)
        docWriter.writeLn("And populate this file with the corresponding property settings (notice I've replaced the option dashes, with property dots):")
        docWriter.writeLn(
                "exclude.tags=8,9,35,10\n" +
                "output.line.format=${DOLLAR}1:${'$'}{msgColor}${DOLLAR}{msgTypeName}${'$'}{colorReset}:${'$'}{msgFix}\n" +
                "input.line.format=^\\d{4}-[01]\\d-[0-3]\\d[T\\s]([0-2]\\d:[0-5]\\d:[0-5]\\d[\\.,]\\d+)?.*?(8=FIX.*\$)", MiscTextEffect.Console)

        examplesList.end()

        return docWriter.toFormattedText()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(ExamplesSection(DocWriterFactory.ConsoleText).toFormattedText())
        }
    }
}