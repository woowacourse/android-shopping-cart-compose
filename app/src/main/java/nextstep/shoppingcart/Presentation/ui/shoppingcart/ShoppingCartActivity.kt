package nextstep.shoppingcart.presentation.ui.shoppingcart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme

class ShoppingCartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingCartTheme {
                ShoppingCartScreen(
                    onBack = ::finish,
                )
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, ShoppingCartActivity::class.java)
    }
}
