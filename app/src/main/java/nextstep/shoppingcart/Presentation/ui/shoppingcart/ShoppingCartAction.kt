package nextstep.shoppingcart.Presentation.ui.shoppingcart

import nextstep.shoppingcart.data.model.Product

sealed interface ShoppingCartAction {
    data class OnItemIncrement(
        val product: Product,
    ) : ShoppingCartAction

    data class OnItemDecrement(
        val product: Product,
    ) : ShoppingCartAction

    data class OnItemRemove(
        val product: Product,
    ) : ShoppingCartAction
}
