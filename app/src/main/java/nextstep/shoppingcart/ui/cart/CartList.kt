package nextstep.shoppingcart.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.domain.CartProduct

@Composable
fun CartList(
    cartItems: List<CartProduct>,
    onCountMinus: (CartProduct) -> Unit,
    onCountPlus: (CartProduct) -> Unit,
    onItemDelete: (CartProduct) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            items = cartItems,
        ) { item ->
            CartItem(
                cartProduct = item,
                onCountMinus = {
                    onCountMinus(item)
                },
                onCountPlus = {
                    onCountPlus(item)
                },
                onItemDelete = {
                    onItemDelete(item)
                },
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun CartListPreview() {
    CartList(
        listOf(
            CartProduct.dummy,
            CartProduct.dummy,
        ),
        {},
        {},
        {},
    )
}
