package nextstep.shoppingcart.ui.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.shoppingcart.data.repository.ProductRepositoryImpl
import nextstep.shoppingcart.ui.shoppingcart.ShoppingCartActivity
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

class ProductDetailActivity : ComponentActivity() {
    private val productId: Long by lazy {
        intent.getLongExtra(PRODUCT_ID, INVALID_PRODUCT_ID)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingCartTheme {
                val product = ProductRepositoryImpl.findProductById(productId)

                if (product == null) {
                    finish()
                    Toast.makeText(this, R.string.product_not_found, Toast.LENGTH_SHORT).show()
                    return@ShoppingCartTheme
                }

                ProductDetailScreen(
                    product = product,
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
        private const val INVALID_PRODUCT_ID = -1L

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
