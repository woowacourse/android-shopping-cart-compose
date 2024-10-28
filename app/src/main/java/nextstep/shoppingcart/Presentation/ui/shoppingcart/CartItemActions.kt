package nextstep.shoppingcart.Presentation.ui.shoppingcart

import nextstep.shoppingcart.data.model.Product

data class CartItemActions(
    val onItemIncrement: (Product) -> Unit,
    val onItemDecrement: (Product) -> Unit,
    val onItemRemove: (Product) -> Unit,
)
