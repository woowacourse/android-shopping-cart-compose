package nextstep.shoppingcart.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.shoppingcart.data.CachedProductDataSource
import nextstep.shoppingcart.data.CachedProductRepository
import nextstep.shoppingcart.data.Cart
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.presentation.components.screens.DetailScreen
import nextstep.shoppingcart.presentation.ui.theme.AndroidshoppingcartTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val product = fetchProduct()
        setContent {
            AndroidshoppingcartTheme {
                DetailScreen(
                    product = product,
                    navigation = { navigateBack() },
                    action = { addToCart(product = product) }
                )
            }
        }
    }

    private fun fetchProduct(): Product {
        val productId =
            intent.getStringExtra(PRODUCT_ID)
                ?: throw IllegalArgumentException("Product ID is required")
        val repository = CachedProductRepository(CachedProductDataSource())
        return repository.findProduct(productId.toLong())
    }

    private fun addToCart(product: Product) {
        Cart.addOne(product)
        navigateToCart()
    }

    private fun navigateToCart() {
        startActivity(CartActivity.createIntent(this))
    }

    private fun navigateBack() = finish()

    companion object {
        private const val PRODUCT_ID = "PRODUCT_ID"

        fun createIntent(
            productId: String,
            context: Context
        ): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(PRODUCT_ID, productId)
            }
    }
}
