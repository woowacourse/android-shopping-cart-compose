package nextstep.shoppingcart.domain

data class CartProduct(
    val product: Product,
    val quantity: Quantity = Quantity(currentValue = INITIAL_COUNT),
    val countInterval: Int = DEFAULT_COUNT_INTERVAL,
) {
    val totalPrice: Int = product.price * quantity.currentValue

    fun increaseCount(): CartQuantityUpdateResult =
        if ((quantity.maxValue != null && quantity.currentValue < quantity.maxValue) || quantity.maxValue == null) {
            CartQuantityUpdateResult.Success(
                this.copy(
                    quantity = quantity.copy(currentValue = quantity.currentValue + countInterval),
                ),
            )
        } else {
            CartQuantityUpdateResult.MaxFail
        }

    fun decreaseCount(): CartQuantityUpdateResult =
        if (quantity.currentValue > quantity.minValue) {
            CartQuantityUpdateResult.Success(
                this.copy(
                    quantity = quantity.copy(currentValue = quantity.currentValue - countInterval),
                ),
            )
        } else {
            CartQuantityUpdateResult.MinFail
        }

    companion object {
        private const val DEFAULT_COUNT_INTERVAL = 1
        private const val INITIAL_COUNT = 1
        val dummy =
            CartProduct(
                ProductRepository.dummy.first(),
            )
    }
}
