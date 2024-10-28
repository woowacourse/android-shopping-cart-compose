package nextstep.shoppingcart.Presentation.ui.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.Presentation.common.currency
import nextstep.shoppingcart.Presentation.component.DefaultTopBar
import nextstep.shoppingcart.Presentation.ui.theme.ShoppingCartTheme
import nextstep.shoppingcart.data.ProductRepository
import nextstep.shoppingcart.domain.Product
import nextstep.signup.R

@Composable
fun ProductsScreen(navigateToProductDetail: (id: Long) -> Unit) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = stringResource(id = R.string.shopping_title),
                actionIconRes = R.drawable.ic_shopping_cart,
            )
        },
    ) { paddingValues ->
        ProductContent(
            products = ProductRepository.getProducts(),
            modifier = Modifier.padding(paddingValues = paddingValues),
            navigateToProductDetail = navigateToProductDetail,
        )
    }
}

@Composable
private fun ProductContent(
    products: List<Product>,
    modifier: Modifier = Modifier,
    navigateToProductDetail: (id: Long) -> Unit,
) {
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(all = 18.dp),
    ) {
        items(
            items = products,
            key = { product -> product.id },
        ) { product ->
            ProductItem(product = product, navigateToProductDetail = navigateToProductDetail)
        }
    }
}

@Composable
private fun ProductItem(
    product: Product,
    navigateToProductDetail: (id: Long) -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier =
            Modifier.clickable { navigateToProductDetail(product.id) },
    ) {
        AsyncImage(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            contentScale = ContentScale.Crop,
            model = product.imageUrl,
            contentDescription = null,
            error = painterResource(R.drawable.ic_launcher_background),
        )
        Text(text = product.name)
        Text(text = product.price.currency(context))
    }
}

@Preview
@Composable
private fun ProductsScreenPreview() {
    ShoppingCartTheme {
        ProductsScreen(navigateToProductDetail = {})
    }
}
