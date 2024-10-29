package nextstep.shoppingcart.ui.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.shoppingcart.ui.shoppingcart.ShoppingCartActivity
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class ProductDetailActivity : ComponentActivity() {
    private val productId: Long by lazy {
        intent.getLongExtra(PRODUCT_ID, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingCartTheme {
                ProductDetailScreen(
                    productId = productId,
                    navigateToBack = ::navigateToBack,
                    navigateToShoppingCart = ::navigateToShoppingCart,
                )
            }
        }
    }

    private fun navigateToBack() {
        finish()
    }

    private fun navigateToShoppingCart() {
        val shoppingCartIntent = ShoppingCartActivity.getIntent(this)
        startActivity(shoppingCartIntent)
    }

    companion object {
        private const val PRODUCT_ID = "productId"

        fun getIntent(
            context: Context,
            productId: Long,
        ): Intent {
            return Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(PRODUCT_ID, productId)
            }
        }
    }
}
