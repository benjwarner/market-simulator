package org.tools4j.fix
/**
 * User: ben
 * Date: 20/6/17
 * Time: 5:43 PM
 */
interface FieldsSource {
    val fields: Fields

    class FixtureNos: FieldsSource by FieldsFromAnnotatedMessageString("35=D|49=CL_COMP_ID|56=EXCHANGE_COMP_ID|11=C1000000|55=AUD/USD|54=2|60=20170609-10:00:00.000|38=9210|40=1", "\\|")
    class FixturePendingNew : FieldsSource by FieldsFromAnnotatedMessageString("35=8|49=EXCHANGE_COMP_ID|56=CL_COMP_ID|37=X1000000|11=C1000000|41=C1000000|55=AUD/USD|54=2|60=20170609-10:00:00.000|17=1|150=A|39=A|151=9210|14=0", "\\|")
    class FixtureNew: FieldsSource by FieldsFromAnnotatedMessageString("35=8|49=EXCHANGE_COMP_ID|56=CL_COMP_ID|37=X1000000|11=C1000000|41=C1000000|55=AUD/USD|54=2|60=20170609-10:00:00.000|17=2|150=0|39=0|151=9210|14=0", "\\|")
    class FixtureCancelled: FieldsSource by FieldsFromAnnotatedMessageString("35=8|49=EXCHANGE_COMP_ID|56=CL_COMP_ID|37=X1000000|11=C1000000|41=C1000000|55=AUD/USD|54=2|60=20170609-10:00:00.316|17=3|150=4|39=4|151=9210|14=0", "\\|")
}

