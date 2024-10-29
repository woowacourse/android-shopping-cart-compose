package nextstep.shoppingcart.ui.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nextstep.shoppingcart.ui.productlist.ProductListScreen
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class ProductDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingCartTheme {
                ProductListScreen()
            }
        }
    }

    companion object {
        private const val EXTRA_PRODUCT_ID = "productId"

        fun newIntent(
            context: Context,
            productId: Long,
        ): Intent =
            Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(EXTRA_PRODUCT_ID, productId)
            }
    }
}

@Composable
fun ProductDetailScreen() {
}

@Preview
@Composable
private fun ProductDetailScreenPreview()  {
    ShoppingCartTheme {
        ProductDetailScreen()
    }
}
