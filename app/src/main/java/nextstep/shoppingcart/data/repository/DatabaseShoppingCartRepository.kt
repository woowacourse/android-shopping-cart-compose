package nextstep.shoppingcart.data.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.ShoppingCartProduct
import nextstep.shoppingcart.domain.repository.ShoppingCartRepository

object DatabaseShoppingCartRepository : ShoppingCartRepository {
    private val _shoppingCartProducts: SnapshotStateList<ShoppingCartProduct> = mutableStateListOf()
    val shoppingCartProducts: List<ShoppingCartProduct> get() = _shoppingCartProducts

    val totalPrice: Int get() = _shoppingCartProducts.sumOf { shoppingCartProduct -> shoppingCartProduct.totalPrice }

    private var currentId: Long = 0L

    override fun addProduct(product: Product) {
        val shoppingCartProduct =
            _shoppingCartProducts.find { shoppingCartProduct ->
                shoppingCartProduct.product == product
            }

        if (shoppingCartProduct == null) {
            _shoppingCartProducts.add(
                ShoppingCartProduct(
                    id = currentId++,
                    product = product,
                    quantity = 1,
                ),
            )
        } else {
            val index = _shoppingCartProducts.indexOf(shoppingCartProduct)
            _shoppingCartProducts[index] = shoppingCartProduct.plusQuantity()
        }
    }

    override fun decreaseProductQuantity(product: Product) {
        val shoppingCartProduct =
            _shoppingCartProducts.find { shoppingCartProduct ->
                shoppingCartProduct.product == product
            }

        if (shoppingCartProduct != null) {
            if (shoppingCartProduct.quantity > 1) {
                val index = _shoppingCartProducts.indexOf(shoppingCartProduct)
                _shoppingCartProducts[index] = shoppingCartProduct.minusQuantity()
            } else {
                _shoppingCartProducts.remove(shoppingCartProduct)
            }
        }
    }

    override fun removeProduct(product: Product) {
        _shoppingCartProducts.removeIf { shoppingCartProduct ->
            shoppingCartProduct.product == product
        }
    }

    override fun findQuantityByProduct(product: Product): Int {
        return _shoppingCartProducts.find { shoppingCartProduct -> shoppingCartProduct.product == product }?.quantity
            ?: 0
    }
}
