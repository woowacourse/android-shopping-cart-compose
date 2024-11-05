package nextstep.shoppingcart.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.shoppingcart.data.CachedProductDataSource
import nextstep.shoppingcart.data.CachedProductRepository
import nextstep.shoppingcart.presentation.components.ListLayout
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = CachedProductRepository(CachedProductDataSource())

        setContent {
            ShoppingCartTheme {
                // A surface container using the 'background' color from the theme
                /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                }*/
                ListLayout(products = repository.fetchProducts())
            }
        }
    }
}
