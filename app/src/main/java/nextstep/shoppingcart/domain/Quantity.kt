package nextstep.shoppingcart.domain

data class Quantity(
    val currentValue: Int,
    val minValue: Int = DEFAULT_MINIMUM_COUNT,
    val maxValue: Int? = null,
) {
    init {
        require(currentValue >= minValue) {
            "$currentValue : 장바구니 상품의 개수는 $minValue 이상이어야 합니다."
        }

        maxValue?.let {
            require(currentValue <= maxValue) {
                "$currentValue : 장바구니 상품의 개수는 $maxValue 이하여야 합니다."
            }
        }
    }

    companion object {
        private const val DEFAULT_MINIMUM_COUNT = 1
    }
}
