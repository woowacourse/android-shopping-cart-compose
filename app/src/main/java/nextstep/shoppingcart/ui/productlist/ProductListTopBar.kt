package nextstep.shoppingcart.ui.productlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.ui.theme.Typography
import nextstep.signup.R

@Composable
fun ProductListTopBar(
    onCartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp, horizontal = 8.dp),
    ) {
        IconButton(onCartClick, modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = stringResource(R.string.product_list_cart_button_desc),
            )
        }
        Text(
            stringResource(R.string.product_list_screen_title),
            modifier = Modifier.align(Alignment.Center),
            style = Typography.headlineMedium,
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun ProductListTopBarPreview() {
    ProductListTopBar({})
}
