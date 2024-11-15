package nextstep.shoppingcart.presentation.components.topbars

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithAction(
    name: String = stringResource(R.string.title_product_list),
    action: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = name,
                style =
                TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        modifier = modifier,
        actions = {
            IconButton(onClick = { action() }) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Shopping Cart"
                )
            }
        }
    )
}

@Preview
@Composable
private fun BarLayoutPreview() {
    Column {
        TopBarWithAction()
    }
}
