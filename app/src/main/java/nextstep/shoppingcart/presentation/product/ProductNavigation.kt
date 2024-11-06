package nextstep.shoppingcart.presentation.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.combine
import kotlinx.serialization.Serializable
import nextstep.shoppingcart.domain.repository.CartRepository
import nextstep.shoppingcart.domain.repository.ProductRepository
import nextstep.shoppingcart.presentation.product.model.ProductDetailUiState
import nextstep.shoppingcart.presentation.product.model.ProductUiModel
import nextstep.shoppingcart.presentation.product.model.ProductUiState

fun NavController.navigateToProductDetail(
    productId: Long,
    navOptions: NavOptions? = null
) = navigate(ProductRoute.Detail(productId), navOptions)

fun NavController.navigateToProductList(navOptions: NavOptions? = null) =
    navigate(ProductRoute.Home, navOptions)

fun NavGraphBuilder.productGraph(
    navigateToCart: () -> Unit,
    navigateToProductDetail: (Long) -> Unit,
    onBack: () -> Unit
) {
    navigation<ProductRoute>(
        startDestination = ProductRoute.Home::class
    ) {
        composable<ProductRoute.Home> {
            val productRepository = ProductRepository.get()
            val cartRepository = CartRepository.get()
            val uiState: ProductUiState by combine(
                productRepository.products(),
                cartRepository.cartProducts()
            ) { products, cartProducts ->
                products.map { product ->
                    val cartProduct = cartProducts.find { it.product.id == product.id }
                    ProductUiModel(
                        id = product.id,
                        imageUrl = product.imageUrl,
                        name = product.name,
                        price = product.price,
                        count = cartProduct?.count ?: 0
                    )
                }.let { products ->
                    if (products.isEmpty()) ProductUiState.Empty
                    else ProductUiState.Success(products)
                }
            }.collectAsStateWithLifecycle(ProductUiState.Empty)

            val onProductPlus: (Long) -> Unit = remember(cartRepository) {
                { cartRepository.addProduct(it, 1) }
            }
            val onProductMinus: (Long) -> Unit = remember(cartRepository) {
                { cartRepository.removeProduct(it, 1) }
            }
            ProductScreen(
                productState = uiState,
                onCartClick = navigateToCart,
                onItemClick = navigateToProductDetail,
                onProductPlus = onProductPlus,
                onProductMinus = onProductMinus,
            )
        }

        composable<ProductRoute.Detail> { backStackEntry ->
            val profile: ProductRoute.Detail = backStackEntry.toRoute()
            val productState by remember(profile.productId) {
                val product = ProductRepository.get().productBy(
                    profile.productId
                )
                val productState = if (product == null) ProductDetailUiState.Error
                else ProductDetailUiState.Success(product)

                mutableStateOf(productState)
            }
            val cartRepository = CartRepository.get()
            val onCartAdd = remember(productState) {
                {
                    when (val currentProductState = productState) {
                        is ProductDetailUiState.Success -> {
                            cartRepository.addProduct(currentProductState.product.id, 1)
                            navigateToCart()
                        }
                        is ProductDetailUiState.Error -> {}
                    }
                }
            }
            ProductDetailScreen(
                productState = productState,
                onBack = onBack,
                onCartAdd = onCartAdd
            )
        }
    }
}

@Serializable
data object ProductRoute {
    @Serializable
    data class Detail(val productId: Long)

    @Serializable
    data object Home
}

