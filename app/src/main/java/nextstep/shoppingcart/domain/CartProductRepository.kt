package nextstep.shoppingcart.domain

object CartProductRepository {
    private val _cartItems: MutableList<CartProduct> = mutableListOf()
    val cartItems: List<CartProduct> get() = _cartItems.toList()

    fun addItem(product: Product) {
        if (_cartItems.any { it.product.id == product.id }) {
            _cartItems.add(
                CartProduct(
                    product = product,
                ),
            )
            return
        }
        plusItemCount(product.id)
    }

    fun minusItemCount(productId: Long) {
        val cartProduct = findItem(productId)
        val index = _cartItems.indexOf(cartProduct)
        when (val result = cartProduct.decreaseCount()) {
            CartQuantityUpdateResult.MaxFail -> throw IllegalArgumentException("수량 감소시에는 수량 증가 실패 이벤트가 발생할 수 없습니다.")
            CartQuantityUpdateResult.MinFail -> deleteCartItem(productId)
            is CartQuantityUpdateResult.Success -> _cartItems[index] = result.product
        }

    }

    fun plusItemCount(productId: Long): CartQuantityUpdateResult {
        val cartProduct = findItem(productId)
        val index = _cartItems.indexOf(cartProduct)
        val result = cartProduct.increaseCount()
        if (result is CartQuantityUpdateResult.Success) {
            _cartItems[index] = result.product
        }
        return result
    }

    fun deleteCartItem(productId: Long) {
        _cartItems.removeIf {
            it.product.id == productId
        }
    }

    fun findItem(productId: Long): CartProduct =
        _cartItems.firstOrNull {
            it.product.id == productId
        } ?: throw IllegalArgumentException("장바구니에 $productId 에 대한 상품이 없습니다.")
}
