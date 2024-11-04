package nextstep.shoppingcart.ui.shoppingcart

import nextstep.shoppingcart.domain.model.Product

sealed interface ShoppingCartAction {
    data class AddProduct(val product: Product) : ShoppingCartAction

    data class DecreaseProductQuantity(val product: Product) : ShoppingCartAction

    data class RemoveProduct(val product: Product) : ShoppingCartAction
}
