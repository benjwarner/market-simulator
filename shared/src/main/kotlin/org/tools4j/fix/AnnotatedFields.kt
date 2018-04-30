package org.tools4j.fix

import java.util.stream.Collectors

/**
 * User: ben
 * Date: 11/04/2018
 * Time: 6:12 PM
 */
class AnnotatedFields(sourceFields: Fields, spec: AnnotationSpec): FieldsSource {
    constructor(sourceFields: Fields, spec: String, boldTagAndValue: Boolean): this(sourceFields, AnnotationSpec(AnnotationPositions.parse(spec), boldTagAndValue))

    override val fields: Fields by lazy {
        FieldsImpl(sourceFields.stream().map { AnnotatedField(it, spec) }.collect(Collectors.toList()))
    }
}