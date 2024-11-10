package nextstep.shoppingcart.domain.model

import java.text.DecimalFormat

data class Price(
    private val amount: Int
) {
    fun format(): String {
        val decimal = DecimalFormat("#,###")
        return decimal.format(amount) + "원"
    }
}
