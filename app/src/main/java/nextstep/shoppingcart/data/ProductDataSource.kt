package nextstep.shoppingcart.data

import nextstep.shoppingcart.domain.model.Product

interface ProductDataSource {
    fun fetchProducts(): List<Product>
    fun findProduct(id: Long): Product
}
