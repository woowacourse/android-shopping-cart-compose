package nextstep.shoppingcart.Presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.Presentation.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun DefaultTopBar(
    title: String,
    modifier: Modifier = Modifier,
    @DrawableRes actionIconRes: Int? = null,
    onAction: (() -> Unit) = {},
) {
    Surface(
        modifier =
            modifier
                .height(DEFAULT_TOP_BAR_HEIGHT)
                .fillMaxWidth(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
        ) {
            Text(
                text = title,
                fontSize = 22.sp,
                modifier = Modifier.align(Alignment.Center),
            )
            actionIconRes?.let { iconRes ->
                DefaultIconButton(
                    modifier =
                        Modifier
                            .align(Alignment.CenterEnd)
                            .size(48.dp),
                    iconRes = iconRes,
                    tint = Color.Black,
                    onClick = onAction,
                )
            }
        }
    }
}

private val DEFAULT_TOP_BAR_HEIGHT = 64.dp

@Preview
@Composable
private fun DefaultTopBarPreview() {
    ShoppingCartTheme {
        DefaultTopBar(
            title = stringResource(id = R.string.app_name),
            actionIconRes = R.drawable.ic_shopping_cart,
        )
    }
}
