package nextstep.shoppingcart.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.shoppingcart.domain.model.CartItem
import nextstep.shoppingcart.domain.model.Product
import nextstep.signup.R

@Composable
fun CartLayout(
    navigation: () -> Unit,
    cartItems: List<CartItem>,
    onIncrease: (CartItem) -> Unit = {},
    onDecrease: (CartItem) -> Unit = {},
    onClear: (CartItem) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column {
        Surface {
            Column {
                TopBarWithNavigation(
                    name = stringResource(R.string.title_shopping_cart),
                    navigation = navigation
                )
                ShoppingCartScreen(
                    items = cartItems,
                    onIncrease = onIncrease,
                    onDecrease = onDecrease,
                    onClear = onClear
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CartLayoutPreview() {
    val items =
        listOf(
            CartItem(
                Product(
                    id = 1L,
                    name = "[든든] 동원 스위트콘",
                    price = 42200,
                    imageUrl = "https://thumbnail7"
                ),
                count = 1
            ),
            CartItem(
                Product(
                    id = 2L,
                    name = "PET보틀-원형(500ml)",
                    price = 84400,
                    imageUrl = ""
                ),
                count = 2
            )
        )
    CartLayout(navigation = {}, cartItems = items)
}
