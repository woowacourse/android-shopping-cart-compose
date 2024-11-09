package nextstep.shoppingcart.ui.productlist

import androidx.compose.foundation.clickable
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
import nextstep.shoppingcart.data.repository.ProductRepositoryImpl
import nextstep.shoppingcart.data.repository.ShoppingCartRepositoryImpl
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.ProductUiModel
import nextstep.shoppingcart.ui.component.ProductItem
import nextstep.shoppingcart.ui.productlist.ProductListAction.AddProduct
import nextstep.shoppingcart.ui.productlist.ProductListAction.DecreaseProductQuantity
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    productItems: List<ProductUiModel>,
    navigateToProductDetail: (Long) -> Unit,
    navigateToShoppingCart: () -> Unit,
) {
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
                        onClick = { navigateToShoppingCart() },
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
            contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 12.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                items = productItems,
                key = { productItem -> productItem.product.id },
            ) { productItem ->
                ProductItem(
                    item = productItem,
                    action = { action ->
                        handleProductListAction(
                            action = action,
                            product = productItem.product,
                        )
                    },
                    modifier =
                        Modifier.clickable {
                            navigateToProductDetail(productItem.product.id)
                        },
                )
            }
        }
    }
}

private fun handleProductListAction(
    action: ProductListAction,
    product: Product,
) {
    when (action) {
        is AddProduct -> {
            ShoppingCartRepositoryImpl.addProduct(product)
        }

        is DecreaseProductQuantity -> {
            ShoppingCartRepositoryImpl.decreaseProductQuantity(product)
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ProductListScreenPreView() {
    ShoppingCartTheme {
        ProductListScreen(
            productItems =
                ProductRepositoryImpl.products.map { product ->
                    ProductUiModel(
                        product = product,
                        quantity = ShoppingCartRepositoryImpl.findQuantityByProduct(product),
                    )
                },
            navigateToProductDetail = {},
            navigateToShoppingCart = {},
        )
    }
}
