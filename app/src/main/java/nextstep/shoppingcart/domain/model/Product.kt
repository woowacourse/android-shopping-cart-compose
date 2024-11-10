package nextstep.shoppingcart.domain.model

data class Product(
    val id: Long,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val isAddedToCart: Boolean = false
) {
    companion object {
        val NULL_PRODUCT = Product(-1, "Null Product", -1000000, "", false)
    }
}
