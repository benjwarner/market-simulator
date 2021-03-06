package org.tools4j.model

import org.tools4j.fix.Field
import org.tools4j.fix.FieldImpl
import org.tools4j.fix.Fields
import org.tools4j.fix.FieldsImpl
import org.tools4j.fix.FieldsSource
import org.tools4j.fix.Id
import org.tools4j.fix.OrdStatus
import org.tools4j.fix.OrderType
import org.tools4j.fix.Price
import org.tools4j.fix.Side

import java.util.ArrayList
import java.util.Collections

/**
 * User: ben
 * Date: 6/06/2017
 * Time: 5:42 PM
 */
class FieldsBuilder : FieldsSource {
    private val fieldsList: MutableList<Field>

    init {
        fieldsList = ArrayList()
    }

    fun with(tag: Int, value: Double?): FieldsBuilder {
        if (value != null) {
            fieldsList.add(FieldImpl(tag, ""+value))
            return this
        } else {
            return this
        }
    }

    fun with(tag: Int, value: Long?): FieldsBuilder {
        if (value != null) {
            fieldsList.add(FieldImpl(tag, ""+value))
            return this
        } else {
            return this
        }
    }

    fun with(tag: Int, value: Boolean?): FieldsBuilder {
        if (value != null) {
            fieldsList.add(FieldImpl(tag, ""+value))
            return this
        } else {
            return this
        }
    }

    fun with(tag: Int, value: Int?): FieldsBuilder {
        if (value != null) {
            fieldsList.add(FieldImpl(tag, ""+value))
            return this
        } else {
            return this
        }
    }

    fun with(tag: Int, value: String?): FieldsBuilder {
        if (value != null) {
            fieldsList.add(FieldImpl(tag, value))
            return this
        } else {
            return this
        }
    }

    fun with(tag: Int, value: OrdStatus?): FieldsBuilder {
        return if (value != null) {
            with(tag, "" + value.code)
        } else {
            this
        }
    }

    fun with(tag: Int, value: OrderType?): FieldsBuilder {
        return if (value != null) {
            with(tag, "" + value.code)
        } else {
            this
        }
    }

    fun with(tag: Int, value: Id?): FieldsBuilder {
        return if (value != null) {
            with(tag, "" + value)
        } else {
            this
        }
    }

    fun with(tag: Int, value: Side?): FieldsBuilder {
        return if (value != null) {
            with(tag, "" + value.code)
        } else {
            this
        }
    }

    fun with(tag: Int, value: Price?): FieldsBuilder {
        return if (value != null) {
            with(tag, value.toString())
        } else {
            this
        }
    }


    override val fields: Fields
        get() = FieldsImpl(Collections.unmodifiableList(fieldsList))

}
