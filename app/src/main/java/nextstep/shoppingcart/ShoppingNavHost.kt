package nextstep.shoppingcart

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import nextstep.shoppingcart.cart.cartScreen
import nextstep.shoppingcart.cart.navigateToCart
import nextstep.shoppingcart.product.ProductRoute
import nextstep.shoppingcart.product.navigateToProductDetail
import nextstep.shoppingcart.product.productGraph


@Composable
fun ShoppingNavHost(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        startDestination = ProductRoute::class
    ) {
        with(navHostController) {
            productGraph(
                navigateToProductDetail = { productId ->
                    navigateToProductDetail(
                        productId,
                    )
                },
                navigateToCart = ::navigateToCart,
                onBack = { popBackStack() },
            )
            cartScreen(
                onBack = { popBackStack() }
            )
        }
    }
}