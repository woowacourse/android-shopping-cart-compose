package nextstep.shoppingcart.domain.repository

import nextstep.shoppingcart.domain.model.Product

interface ShoppingCartRepository {
    fun addProduct(product: Product)

    fun decreaseProductQuantity(product: Product)

    fun removeProduct(product: Product)

    fun findQuantityByProduct(product: Product): Int
}
