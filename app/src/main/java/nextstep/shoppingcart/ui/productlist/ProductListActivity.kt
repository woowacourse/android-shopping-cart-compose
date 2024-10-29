package nextstep.shoppingcart.ui.productlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.shoppingcart.ui.productdetail.ProductDetailActivity
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class ProductListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingCartTheme {
                ProductListScreen(navigateToProductDetail = ::navigateToProductDetail)
            }
        }
    }

    private fun navigateToProductDetail(productId: Long) {
        startActivity(
            ProductDetailActivity.getIntent(
                context = this,
                productId = productId,
            ),
        )
    }
}
