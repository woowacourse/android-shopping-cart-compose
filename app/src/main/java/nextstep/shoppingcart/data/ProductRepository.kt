package nextstep.shoppingcart.data

import nextstep.shoppingcart.domain.model.Product

interface ProductRepository {
    fun fetchProducts(): List<Product>
    fun findProduct(id: Long): Product
}
