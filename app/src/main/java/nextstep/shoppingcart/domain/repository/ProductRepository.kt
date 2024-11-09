package nextstep.shoppingcart.domain.repository

import nextstep.shoppingcart.domain.model.Product

interface ProductRepository {
    fun findProductById(productId: Long): Product?
}
