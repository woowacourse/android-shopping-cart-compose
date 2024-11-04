package nextstep.shoppingcart.ui.productlist

import nextstep.shoppingcart.domain.model.Product

sealed interface ProductListAction {
    data class AddProduct(val product: Product) : ProductListAction

    data class DecreaseProductQuantity(val product: Product) : ProductListAction
}
