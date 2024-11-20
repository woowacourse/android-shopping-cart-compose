package nextstep.shoppingcart.presentation.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.data.Cart
import nextstep.shoppingcart.domain.model.CartItem
import nextstep.shoppingcart.domain.model.Price
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.presentation.components.BottomBar
import nextstep.shoppingcart.presentation.components.CartItemList
import nextstep.shoppingcart.presentation.components.topbars.TopBarWithNavigation
import nextstep.shoppingcart.presentation.ui.theme.AndroidshoppingcartTheme
import nextstep.shoppingcart.ui.theme.Blue50
import nextstep.signup.R

@Composable
fun CartScreen(
    navigation: () -> Unit,
    cartItems: List<CartItem>,
    totalPrice: Int,
    onIncrease: (CartItem) -> Unit = {},
    onDecrease: (CartItem) -> Unit = {},
    onClear: (CartItem) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    AndroidshoppingcartTheme {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopBarWithNavigation(
                    name = stringResource(R.string.title_shopping_cart),
                    navigation = navigation,
                )
            },
            bottomBar = {
                BottomBar(
                    text = stringResource(R.string.order, Price(totalPrice).format()),
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(Blue50)
                        .padding(16.dp),
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                CartItemList(
                    items = cartItems,
                    onIncrease = onIncrease,
                    onDecrease = onDecrease,
                    onClear = onClear,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CartScreenPreview() {
    val items =
        listOf(
            CartItem(
                id = 0L,
                Product(
                    id = 1L,
                    name = "[든든] 동원 스위트콘",
                    price = 42200,
                    imageUrl = "https://thumbnail7",
                ),
                count = 1,
            ),
            CartItem(
                id = 1L,
                Product(
                    id = 2L,
                    name = "PET보틀-원형(500ml)",
                    price = 84400,
                    imageUrl = "",
                ),
                count = 2,
            ),
        )
    CartScreen(navigation = {}, cartItems = items, totalPrice = items.sumOf { it.product.price * it.count })
}
