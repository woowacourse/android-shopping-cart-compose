package nextstep.shoppingcart.ui.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

class ProductDetailActivity : ComponentActivity() {
    private val productId: Long by lazy {
        intent.getLongExtra(PRODUCT_ID, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (productId < 0) {
            Toast.makeText(
                this,
                resources.getString(R.string.invalid_product_id),
                Toast.LENGTH_SHORT,
            ).show()
            finish()
        }

        setContent {
            ShoppingCartTheme {
                ProductDetailScreen(productId = productId, navigateToBack = ::navigateToBack)
            }
        }
    }

    private fun navigateToBack() {
        finish()
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
