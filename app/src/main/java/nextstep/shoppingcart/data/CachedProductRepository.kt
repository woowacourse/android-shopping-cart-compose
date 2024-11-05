package nextstep.shoppingcart.data

import nextstep.shoppingcart.domain.model.Product

class CachedProductRepository(
    private val productDataSource: ProductDataSource,
) : ProductRepository {
    override fun fetchProducts(): List<Product> = productDataSource.fetchProducts()

    override fun findProduct(id: Long): Product = productDataSource.findProduct(id)
}
