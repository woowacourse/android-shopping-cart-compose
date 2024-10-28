package nextstep.shoppingcart.Presentation.ui.shoppingcart

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.shoppingcart.Presentation.component.BackButtonTopBar
import nextstep.shoppingcart.Presentation.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun ShoppingCartScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            BackButtonTopBar(
                title = stringResource(id = R.string.shopping_cart_title),
                onBack = onBack,
            )
        },
    ) { paddingValues ->
    }
}

@Preview
@Composable
private fun ShoppingCartScreenPreview() {
    ShoppingCartTheme {
        ShoppingCartScreen(onBack = {})
    }
}
