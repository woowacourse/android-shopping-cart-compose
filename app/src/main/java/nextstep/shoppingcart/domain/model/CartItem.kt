package nextstep.shoppingcart.domain.model

data class CartItem(
    val id: Long = maxCartItemId++,
    val product: Product,
    val count: Int
) {
    companion object {
        private var maxCartItemId: Long = 0L
    }
}
