package nextstep.shoppingcart.domain.model

data class CartItem(
    val id: Long,
    val product: Product,
    val count: Int
) {
    val totalPrice: Int get() = product.price * count
}
