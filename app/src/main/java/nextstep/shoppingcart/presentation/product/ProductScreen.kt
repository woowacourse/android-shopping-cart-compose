package nextstep.shoppingcart.presentation.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.R
import nextstep.shoppingcart.presentation.product.component.ProductItem
import nextstep.shoppingcart.presentation.product.model.ProductUiModel
import nextstep.shoppingcart.presentation.product.model.ProductUiState
import nextstep.shoppingcart.presentation.product.preview.ProductPreviewParameterProvider
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    productState: ProductUiState,
    onItemClick: (id: Long) -> Unit,
    onCartClick: () -> Unit,
    onProductPlus: (id: Long) -> Unit,
    onProductMinus: (id: Long) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.product_screen_title))
                },
                actions = {
                    IconButton(onClick = onCartClick) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = stringResource(id = R.string.product_top_bar_actions_content_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when (productState) {
            is ProductUiState.Empty -> ProductEmptyContent(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )

            is ProductUiState.Success -> ProductContent(
                products = productState.products,
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(start = 18.dp, end = 18.dp, bottom = 20.dp)
                    .fillMaxSize(),
                onItemClick = onItemClick,
                onProductPlus = onProductPlus,
                onProductMinus = onProductMinus
            )
        }
    }
}

@Composable
private fun ProductContent(
    products: List<ProductUiModel>,
    modifier: Modifier = Modifier,
    onItemClick: (id: Long) -> Unit,
    onProductPlus: (id: Long) -> Unit,
    onProductMinus: (id: Long) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products, key = { it.id }) { product ->
            ProductItem(
                product,
                onClick = onItemClick,
                count = product.count,
                onProductPlus = { onProductPlus(product.id) },
                onProductMinus = { onProductMinus(product.id) },
            )
        }
    }
}

@Composable
private fun ProductEmptyContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.product_screen_empty_content),
            style = MaterialTheme.typography.displayMedium
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewProductScreen(
    @PreviewParameter(ProductPreviewParameterProvider::class) productState: ProductUiState
) {
    ShoppingCartTheme {
        ProductScreen(productState, {}, {}, {}, {})
    }
}