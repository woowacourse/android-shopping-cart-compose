package nextstep.shoppingcart.domain

sealed interface CartQuantityUpdateResult {
    data class Success(val product: CartProduct) : CartQuantityUpdateResult

    data object MinFail : CartQuantityUpdateResult

    data object MaxFail : CartQuantityUpdateResult
}
