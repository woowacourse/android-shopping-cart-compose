package nextstep.shoppingcart.ui.productlist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.ui.theme.Typography
import nextstep.signup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListTopBar(
    onCartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(R.string.product_list_screen_title),
                style = Typography.headlineMedium,
                modifier = Modifier.wrapContentHeight().padding(vertical = 18.dp),
            )
        },
        actions = {
            IconButton(onCartClick, modifier = Modifier.padding(end = 8.dp)) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = stringResource(R.string.product_list_cart_button_desc),
                )
            }
        },
        modifier =
            modifier.fillMaxWidth(),
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun ProductListTopBarPreview() {
    ProductListTopBar({})
}
