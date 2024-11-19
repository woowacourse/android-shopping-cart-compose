package nextstep.shoppingcart.presentation.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.presentation.components.ProductList
import nextstep.shoppingcart.presentation.components.topbars.TopBarWithAction
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
fun HomeScreen(
    products: List<Product>,
    action: () -> Unit = {},
    onItemClick: (Long) -> Unit = {},
    modifier: Modifier = Modifier
) {
    ShoppingCartTheme {
        Column(
            modifier = modifier
        ) {
            TopBarWithAction(modifier = Modifier, action = action)
            ProductList(
                items = products,
                onItemClick = onItemClick,
                modifier = Modifier.padding(top = 18.dp, start = 13.dp, end = 13.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    val products =
        listOf(
            Product(
                id = 1,
                name = "name1",
                price = 1000,
                imageUrl = "https://thumbnail7.coupangcdn.com/thumbnails/remote/292x292ex/image/retail/images/1263603036762773-f6291401-9c64-4944-8189-86e5aead6049.png"
            ),
            Product(
                id = 2,
                name = "name2",
                price = 2000,
                imageUrl = "https://thumbnail7.coupangcdn.com/thumbnails/remote/292x292ex/image/vendor_inventory/e294/d78edd81a8f38ae32984e9ad3393a840bd9ccdc91161838a8036f4d90434.jpg"
            ),
            Product(
                id = 3,
                name = "name3",
                price = 3000,
                imageUrl = "https://thumbnail7.coupangcdn.com/thumbnails/remote/292x292ex/image/1025_amir_coupang_oct_80k/e308/9c53df34079cb2e6a8123f93355a796ae18b7979bc61bd360da0793314af.jpg"
            )
        )

    HomeScreen(products = products)
}
