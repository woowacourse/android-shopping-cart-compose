package nextstep.shoppingcart.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CartLayout(
    navigation: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Surface {
            TopBarWithNavigation(
                name = "장바구니",
                navigation = navigation,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CartLayoutPreview() {
    CartLayout(navigation = {})
}
