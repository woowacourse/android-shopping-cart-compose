package nextstep.shoppingcart.data.repository

import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.ShoppingCartProduct
import nextstep.shoppingcart.domain.model.ShoppingCartProduct.Companion.MIN_QUANTITY

object DefaultShoppingCartRepository {
    private val _shoppingCartProducts: MutableList<ShoppingCartProduct> = mutableListOf()
    val shoppingCartProducts: List<ShoppingCartProduct> get() = _shoppingCartProducts.toList()

    val totalPrice: Int
        get() =
            _shoppingCartProducts.sumOf { shoppingCartProduct ->
                shoppingCartProduct.totalPrice
            }

    fun addProduct(product: Product) {
        val shoppingCartProduct =
            _shoppingCartProducts.find { shoppingCartProduct ->
                shoppingCartProduct.product == product
            }

        if (shoppingCartProduct == null) {
            _shoppingCartProducts.add(ShoppingCartProduct(product, MIN_QUANTITY))
        } else {
            val index = _shoppingCartProducts.indexOf(shoppingCartProduct)
            _shoppingCartProducts[index] = shoppingCartProduct.plusQuantity()
        }
    }

    fun decreaseProductQuantity(product: Product) {
        val shoppingCartProduct =
            _shoppingCartProducts.find { shoppingCartProduct ->
                shoppingCartProduct.product == product
            }

        if (shoppingCartProduct != null) {
            if (shoppingCartProduct.quantity > MIN_QUANTITY) {
                val index = _shoppingCartProducts.indexOf(shoppingCartProduct)
                _shoppingCartProducts[index] = shoppingCartProduct.minusQuantity()
            } else {
                _shoppingCartProducts.remove(shoppingCartProduct)
            }
        }
    }

    fun removeProduct(product: Product) {
        _shoppingCartProducts.removeAll { shoppingCartProduct ->
            shoppingCartProduct.product == product
        }
    }
}
