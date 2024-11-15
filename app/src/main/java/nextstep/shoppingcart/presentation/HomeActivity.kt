package nextstep.shoppingcart.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.shoppingcart.data.CachedProductDataSource
import nextstep.shoppingcart.data.CachedProductRepository
import nextstep.shoppingcart.presentation.components.screens.HomeScreen
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = CachedProductRepository(CachedProductDataSource())

        setContent {
            ShoppingCartTheme {
                HomeScreen(
                    products = repository.fetchProducts(),
                    action = { navigateToCart() },
                    onItemClick = { productId -> navigateToDetail(productId) }
                )
            }
        }
    }

    private fun navigateToCart() {
        startActivity(CartActivity.createIntent(this))
    }

    private fun navigateToDetail(productId: Long) {
        startActivity(DetailActivity.createIntent(productId.toString(), this))
    }
}
