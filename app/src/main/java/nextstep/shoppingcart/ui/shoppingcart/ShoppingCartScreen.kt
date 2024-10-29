package nextstep.shoppingcart.ui.shoppingcart

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.shoppingcart.ui.component.BackNavigationTopAppBar
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun ShoppingCartScreen(navigateToBack: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavigationTopAppBar(
                title = stringResource(R.string.shopping_cart),
                navigateToBack = navigateToBack,
            )
        },
    ) { contentPadding ->
    }
}

@Composable
@Preview(showBackground = true)
fun ShoppingCartScreenPreview() {
    ShoppingCartTheme {
        ShoppingCartScreen(navigateToBack = {})
    }
}
