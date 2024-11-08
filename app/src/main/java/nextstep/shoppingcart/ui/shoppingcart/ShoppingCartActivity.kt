package nextstep.shoppingcart.ui.shoppingcart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.shoppingcart.data.repository.ShoppingCartRepositoryImpl
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class ShoppingCartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val shoppingCartProducts = ShoppingCartRepositoryImpl.shoppingCartProducts

            ShoppingCartTheme {
                ShoppingCartScreen(
                    shoppingCartProducts = shoppingCartProducts,
                    navigateToBack = ::navigateToBack
                )
            }
        }
    }

    private fun navigateToBack() {
        finish()
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, ShoppingCartActivity::class.java)
        }
    }
}
