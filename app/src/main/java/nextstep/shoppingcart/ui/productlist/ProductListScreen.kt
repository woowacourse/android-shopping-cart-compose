package nextstep.shoppingcart.ui.productlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.product_list),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                actions = {
                    IconButton(
                        onClick = { /* 장바구니 화면으로 이동 */ },
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = stringResource(R.string.shopping_cart),
                        )
                    }
                },
            )
        },
    ) { contentPadding ->
        Text(text = "", modifier = Modifier.padding(contentPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreView() {
    ShoppingCartTheme {
        ProductListScreen()
    }
}
