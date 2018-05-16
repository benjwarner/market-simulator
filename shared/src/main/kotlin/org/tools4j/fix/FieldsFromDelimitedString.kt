package org.tools4j.fix

import java.util.ArrayList

/**
 * User: ben
 * Date: 31/8/17
 * Time: 5:14 PM
 */
class FieldsFromDelimitedString(private val str: String, private val delimiter: Char = Ascii1Char().toChar()) : FieldsSource {

    constructor(str: String, delimiter: String) : this(str, delimiter.toCharArray()[0]) {
        if(delimiter.length > 1){
            throw IllegalArgumentException("Delimiter must be just one character long [$delimiter]")
        }
    }

    override val fields: Fields by lazy {
        val fields = ArrayList<Field>()
        val split = str.split(delimiter)
        if(split.size > 0 && !(split.size == 1 && split[0].isEmpty())) {
            val fieldStrings = split.iterator()
            while (fieldStrings.hasNext()) {
                val fieldStr = fieldStrings.next()
                val tagAndValue = SplitableByCharString(fieldStr, '=').splitAtFirst().values()
                val field = FieldImpl(tagAndValue!![0], tagAndValue[1])
                fields.add(field)
            }
        }
        FieldsImpl(fields)
    }

    override fun toString(): String {
        return fields.toConsoleText(delimiter.toString());
    }
}
