package nextstep.shoppingcart.ui.shoppingcart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.data.repository.DefaultShoppingCartRepository
import nextstep.shoppingcart.domain.model.ShoppingCartProduct
import nextstep.shoppingcart.domain.model.ShoppingCartProducts
import nextstep.shoppingcart.ui.component.BackNavigationTopAppBar
import nextstep.shoppingcart.ui.component.RectangleButton
import nextstep.shoppingcart.ui.component.ShoppingCartItem
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun ShoppingCartScreen(navigateToBack: () -> Unit) {
    val listState = rememberLazyListState()
    var shoppingCartProducts by rememberSaveable {
        mutableStateOf(
            ShoppingCartProducts(
                items = DefaultShoppingCartRepository.shoppingCartProducts,
            ),
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavigationTopAppBar(
                title = stringResource(R.string.shopping_cart),
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                navigateToBack = navigateToBack,
            )
        },
    ) { contentPadding ->
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            LazyColumn(
                state = listState,
            ) {
                items(
                    items = shoppingCartProducts.items,
                    key = { item -> item.product.id },
                ) { shoppingCartProduct ->
                    ShoppingCartItem(shoppingCartProduct = shoppingCartProduct, action = { action ->
                        handleShoppingCartAction(action, shoppingCartProduct) { updatedProducts ->
                            shoppingCartProducts =
                                shoppingCartProducts.copy(items = updatedProducts)
                        }
                    })
                }
            }

            RectangleButton(
                text = stringResource(R.string.order_price, shoppingCartProducts.totalPrice),
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                onClick = {},
            )
        }
    }
}

private fun handleShoppingCartAction(
    action: ShoppingCartAction,
    shoppingCartProduct: ShoppingCartProduct,
    updateShoppingCartProducts: (List<ShoppingCartProduct>) -> Unit,
) {
    when (action) {
        is ShoppingCartAction.PlusQuantity ->
            DefaultShoppingCartRepository.addProduct(shoppingCartProduct.product)

        is ShoppingCartAction.MinusQuantity ->
            DefaultShoppingCartRepository.decreaseProductQuantity(shoppingCartProduct.product)

        is ShoppingCartAction.RemoveProduct ->
            DefaultShoppingCartRepository.removeProduct(shoppingCartProduct.product)
    }
    updateShoppingCartProducts(DefaultShoppingCartRepository.shoppingCartProducts)
}

@Composable
@Preview(showBackground = true)
fun ShoppingCartScreenPreview() {
    ShoppingCartTheme {
        ShoppingCartScreen(navigateToBack = {})
    }
}
