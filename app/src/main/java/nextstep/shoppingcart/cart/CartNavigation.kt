package nextstep.shoppingcart.cart

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavController.navigateToCart(navOptions: NavOptions? = null) =
    navigate(CartScreen, navOptions)


fun NavGraphBuilder.cartScreen(
    onBack: () -> Unit
) {
    composable<CartScreen> {
        CartScreen(onBack = onBack)
    }
}

@Serializable
data object CartScreen
