package nextstep.shoppingcart.ui.cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nextstep.shoppingcart.ui.productdetail.ProductDetailScreen
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class CartActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingCartTheme {
                CartScreen()
            }
        }
    }

    companion object {
        fun newIntent(context: Context):Intent = Intent(context, CartActivity::class.java)
    }
}

@Composable
fun CartScreen() {

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun CartScreenPreview() {
    ShoppingCartTheme {
        CartScreen()
    }
}