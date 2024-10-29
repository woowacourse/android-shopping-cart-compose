package nextstep.shoppingcart.ui.productlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.ui.productlist.model.products
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
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                        onClick = { /* 장바구니 화면으로 이동 */ },
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
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 18.dp, vertical = 12.dp),
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState()
        ) {
            items(products.size) { index ->
                val itemPadding = if (index % 2 == 0) {
                    Modifier.padding(end = 8.dp, bottom = 20.dp)
                } else {
                    Modifier.padding(start = 8.dp, bottom = 20.dp)
                }

                val product = products[index]
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(itemPadding)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(158.dp),
                        model = product.imageUrl,
                        contentDescription = product.name,
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 8.dp),
                        text = product.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        text = stringResource(R.string.price_format, product.price),
                        fontSize = 16.sp,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreView() {
    ShoppingCartTheme {
        ProductListScreen()
    }
}
