package nextstep.shoppingcart.presentation.product.model

import nextstep.shoppingcart.domain.model.Product

sealed class ProductDetailUiState {
    data class Success(val product: Product) : ProductDetailUiState()
    data object Error : ProductDetailUiState()
}