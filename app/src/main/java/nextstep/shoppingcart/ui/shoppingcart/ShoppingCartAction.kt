package nextstep.shoppingcart.ui.shoppingcart

import nextstep.shoppingcart.domain.model.Product

sealed interface ShoppingCartAction {
    data class PlusQuantity(val product: Product) : ShoppingCartAction

    data class MinusQuantity(val product: Product) : ShoppingCartAction

    data class RemoveProduct(val product: Product) : ShoppingCartAction
}
