package nextstep.shoppingcart.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import nextstep.shoppingcart.presentation.components.CartLayout
import nextstep.shoppingcart.presentation.ui.theme.AndroidshoppingcartTheme

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidshoppingcartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CartLayout(
                        navigation = { finish() },
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, CartActivity::class.java)
    }
}
