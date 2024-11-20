package nextstep.shoppingcart.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import nextstep.shoppingcart.data.Cart
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.presentation.components.screens.CartScreen

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val cartItems = remember { Cart.items }
            val totalPrice by Cart.totalPrice
            CartScreen(
                navigation = { finish() },
                cartItems = cartItems,
                totalPrice = totalPrice,
                onIncrease = { addItem(it.product) },
                onDecrease = { subItem(it.product) },
                onClear = { removeItem(it.product) }
            )
        }
    }

    private fun addItem(item: Product) = Cart.addOne(item)

    private fun subItem(item: Product) = Cart.removeOne(item)

    private fun removeItem(item: Product) = Cart.removeAll(item)

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, CartActivity::class.java)
    }
}
