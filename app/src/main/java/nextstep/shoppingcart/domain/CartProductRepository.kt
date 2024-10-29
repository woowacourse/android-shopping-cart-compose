package nextstep.shoppingcart.domain

object CartProductRepository {
    private val cartItems: MutableList<CartProduct> = mutableListOf()

    fun addItem(product: Product) {
        if (cartItems.any { it.product.id == product.id }) {
            cartItems.add(
                CartProduct(
                    product = product,
                ),
            )
            return
        }
        plusItemCount(product)
    }

    fun minusItemCount(product: Product): CartQuantityUpdateResult {
        val cartProduct = findItem(product.id)
        val index = cartItems.indexOf(cartProduct)
        val result = cartProduct.decreaseCount()
        if (result is CartQuantityUpdateResult.Success) {
            cartItems[index] = result.product
        }
        return result
    }

    fun plusItemCount(product: Product): CartQuantityUpdateResult {
        val cartProduct = findItem(product.id)
        val index = cartItems.indexOf(cartProduct)
        val result = cartProduct.increaseCount()
        if (result is CartQuantityUpdateResult.Success) {
            cartItems[index] = result.product
        }
        return result
    }

    fun findItem(productId: Long): CartProduct =
        cartItems.firstOrNull {
            it.product.id == productId
        } ?: throw IllegalArgumentException("장바구니에 $productId 에 대한 상품이 없습니다.")
}
