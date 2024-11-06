package nextstep.shoppingcart.presentation.product.model

sealed class ProductUiState {
    data class Success(val products: List<ProductUiModel>) : ProductUiState()
    data object Empty : ProductUiState()
}