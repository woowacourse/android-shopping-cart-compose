package nextstep.shoppingcart.ui.productlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import nextstep.shoppingcart.ui.component.ProductItem
import nextstep.shoppingcart.ui.productlist.model.products
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navigateToProductDetail: (Long) -> Unit) {
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(contentPadding),
            state = rememberLazyGridState(),
            contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(products) { product ->
                ProductItem(
                    product = product,
                    onClick = {
                        navigateToProductDetail(product.id)
                    },
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductListScreenPreView() {
    ShoppingCartTheme {
        ProductListScreen(navigateToProductDetail = {})
    }
}
