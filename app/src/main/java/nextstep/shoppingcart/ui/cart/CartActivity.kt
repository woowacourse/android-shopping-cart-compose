package nextstep.shoppingcart.ui.cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.shoppingcart.domain.CartProductRepository
import nextstep.shoppingcart.ui.component.BackNavigationTopBar
import nextstep.shoppingcart.ui.component.SubmitButton
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.shoppingcart.ui.util.findActivity
import nextstep.signup.R

class CartActivity : ComponentActivity() {
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
        fun newIntent(context: Context): Intent = Intent(context, CartActivity::class.java)
    }
}

@Composable
fun CartScreen() {
    val context = LocalContext.current
    var totalPrice by remember { mutableIntStateOf(CartProductRepository.totalPrice()) }
    var cartItems by remember { mutableStateOf(CartProductRepository.cartItems) }

    fun updateInfo() {
        totalPrice = CartProductRepository.totalPrice()
        cartItems = CartProductRepository.cartItems
    }

    Scaffold(
        topBar = {
            BackNavigationTopBar(
                title = stringResource(R.string.cart_title),
            ) {
                context.findActivity().finish()
            }
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
        ) {
            CartList(
                cartItems = cartItems,
                onCountMinus = { cartProduct ->
                    CartProductRepository.minusItemCount(cartProduct.product.id)
                    updateInfo()
                },
                onCountPlus = { cartProduct ->
                    CartProductRepository.plusItemCount(cartProduct.product.id)
                    updateInfo()
                },
                onItemDelete = { cartProduct ->
                    CartProductRepository.deleteCartItem(cartProduct.product.id)
                    updateInfo()
                },
            )

            SubmitButton(
                label =
                    stringResource(
                        R.string.cart_order_button,
                        totalPrice,
                    ),
                onClick = {},
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun CartScreenPreview() {
    ShoppingCartTheme {
        CartScreen()
    }
}
