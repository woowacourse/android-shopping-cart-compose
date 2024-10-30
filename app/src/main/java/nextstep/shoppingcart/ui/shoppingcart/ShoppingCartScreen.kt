package nextstep.shoppingcart.ui.shoppingcart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.shoppingcart.data.repository.DefaultShoppingCartRepository
import nextstep.shoppingcart.ui.component.BackNavigationTopAppBar
import nextstep.shoppingcart.ui.component.RectangleButton
import nextstep.shoppingcart.ui.component.ShoppingCartItem
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun ShoppingCartScreen(navigateToBack: () -> Unit) {
    var shoppingCartProducts by rememberSaveable {
        mutableStateOf(
            DefaultShoppingCartRepository.shoppingCartProducts,
        )
    }

    var totalPrice by rememberSaveable {
        mutableIntStateOf(
            DefaultShoppingCartRepository.totalPrice,
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavigationTopAppBar(
                title = stringResource(R.string.shopping_cart),
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
            val listState = rememberLazyListState()

            LazyColumn(
                state = listState,
            ) {
                items(
                    items = shoppingCartProducts,
                    key = { shoppingCartProduct -> shoppingCartProduct.product.id },
                ) { shoppingCartProduct ->
                    ShoppingCartItem(shoppingCartProduct = shoppingCartProduct, action = { action ->
                        when (action) {
                            is ShoppingCartAction.PlusQuantity ->
                                DefaultShoppingCartRepository.addProduct(
                                    shoppingCartProduct.product,
                                )

                            is ShoppingCartAction.MinusQuantity ->
                                DefaultShoppingCartRepository.decreaseProductQuantity(
                                    shoppingCartProduct.product,
                                )

                            is ShoppingCartAction.RemoveProduct ->
                                DefaultShoppingCartRepository.removeProduct(
                                    shoppingCartProduct.product,
                                )
                        }
                        shoppingCartProducts = DefaultShoppingCartRepository.shoppingCartProducts
                        totalPrice = DefaultShoppingCartRepository.totalPrice
                    })
                }
            }

            RectangleButton(
                text = stringResource(R.string.order_price, totalPrice),
                onClick = {},
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShoppingCartScreenPreview() {
    ShoppingCartTheme {
        ShoppingCartScreen(navigateToBack = {})
    }
}
