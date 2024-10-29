package nextstep.shoppingcart.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.data.model.CartItem
import nextstep.shoppingcart.data.model.Product
import nextstep.shoppingcart.data.repository.ProductRepository
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun CartItemCounter(
    cartItem: CartItem,
    onItemIncremented: (Product) -> Unit,
    onItemDecremented: (Product) -> Unit,
) {
    Row {
        Icon(
            painter = painterResource(R.drawable.ic_remove),
            contentDescription = stringResource(R.string.decremented),
            modifier = Modifier.clickable { onItemDecremented(cartItem.product) },
        )
        Text(
            modifier = Modifier.padding(horizontal = 15.dp),
            text = cartItem.count.toString(),
            fontSize = 22.sp,
        )
        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = stringResource(R.string.increment),
            modifier = Modifier.clickable { onItemIncremented(cartItem.product) },
        )
    }
}

@Preview
@Composable
private fun CartItemCounterPreview() {
    val product = ProductRepository.getProducts().first()
    val cartItem = CartItem(product, 0)
    ShoppingCartTheme {
        CartItemCounter(cartItem, {}, {})
    }
}
