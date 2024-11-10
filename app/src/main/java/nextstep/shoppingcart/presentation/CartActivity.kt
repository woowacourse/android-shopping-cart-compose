package nextstep.shoppingcart.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import nextstep.shoppingcart.data.Cart
import nextstep.shoppingcart.domain.model.CartItem
import nextstep.shoppingcart.presentation.components.CartLayout
import nextstep.shoppingcart.presentation.ui.theme.AndroidshoppingcartTheme

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var cartItems by remember { mutableStateOf(Cart.items) }
            AndroidshoppingcartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CartLayout(
                        navigation = { finish() },
                        modifier = Modifier.padding(innerPadding),
                        cartItems = cartItems,
                        onIncrease = {
                            onIncrease(it)
                            cartItems = Cart.items
                        },
                        onDecrease = {
                            onDecrease(it)
                            cartItems = Cart.items
                        },
                        onClear = {
                            onRemove(it)
                            cartItems = Cart.items
                        }
                    )
                }
            }
        }
    }

    private fun onIncrease(item: CartItem) {
        Cart.addOne(item.product)
    }

    private fun onDecrease(item: CartItem) {
        Cart.removeOne(item.product)
    }

    private fun onRemove(item: CartItem) {
        Cart.removeAll(item.product)
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, CartActivity::class.java)
    }
}
