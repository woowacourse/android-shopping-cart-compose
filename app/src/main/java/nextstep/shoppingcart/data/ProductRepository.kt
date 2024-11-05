package nextstep.shoppingcart.data

import nextstep.shoppingcart.domain.model.Product

interface ProductRepository {
    fun fetchProducts(): List<Product>
    fun getProduct(id: Long): Product
}
