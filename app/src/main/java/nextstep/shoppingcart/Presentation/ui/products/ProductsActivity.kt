package nextstep.shoppingcart.Presentation.ui.products

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.shoppingcart.Presentation.ui.productdetail.ProductDetailActivity
import nextstep.shoppingcart.Presentation.ui.theme.ShoppingCartTheme

class ProductsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingCartTheme {
                ProductsScreen(navigateToProductDetail = ::startProductDetailActivity)
            }
        }
    }

    fun startProductDetailActivity(id: Long) = startActivity(ProductDetailActivity.getIntent(this, id))
}
