package nextstep.shoppingcart.Presentation.ui.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.shoppingcart.Presentation.ui.theme.ShoppingCartTheme

class ProductDetailActivity : ComponentActivity() {
    private val productId: Long by lazy { intent.getLongExtra(PUT_EXTRA_PRODUCT_ID, 0L) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingCartTheme {
                ProductDetailScreen(
                    id = productId,
                    onBack = ::finish,
                )
            }
        }
    }

    companion object {
        const val PUT_EXTRA_PRODUCT_ID = "PUT_EXTRA_PRODUCT_ID"

        fun getIntent(
            context: Context,
            id: Long,
        ): Intent = Intent(context, ProductDetailActivity::class.java).putExtra(PUT_EXTRA_PRODUCT_ID, id)
    }
}
