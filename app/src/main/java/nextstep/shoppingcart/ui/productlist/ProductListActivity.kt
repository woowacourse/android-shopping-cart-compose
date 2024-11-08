package nextstep.shoppingcart.ui.productlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.shoppingcart.data.repository.DatabaseProductRepository
import nextstep.shoppingcart.data.repository.DatabaseShoppingCartRepository
import nextstep.shoppingcart.domain.model.ProductUiModel
import nextstep.shoppingcart.ui.productdetail.ProductDetailActivity
import nextstep.shoppingcart.ui.shoppingcart.ShoppingCartActivity
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class ProductListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val productItems: List<ProductUiModel> =
                DatabaseProductRepository.products.map { product ->
                    ProductUiModel(
                        product = product,
                        quantity = DatabaseShoppingCartRepository.findQuantityByProduct(product),
                    )
                }

            ShoppingCartTheme {
                ProductListScreen(
                    productItems = productItems,
                    navigateToProductDetail = ::navigateToProductDetail,
                    navigateToShoppingCart = ::navigateToShoppingCart,
                )
            }
        }
    }

    private fun navigateToProductDetail(productId: Long) {
        val productDetailIntent =
            ProductDetailActivity.getIntent(
                context = this,
                productId = productId,
            )
        startActivity(productDetailIntent)
    }

    private fun navigateToShoppingCart() {
        val shoppingCartIntent = ShoppingCartActivity.getIntent(this)
        startActivity(shoppingCartIntent)
    }
}
