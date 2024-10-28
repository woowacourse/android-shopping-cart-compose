package nextstep.shoppingcart.Presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun BackButtonTopBar(
    modifier: Modifier = Modifier,
    @DrawableRes backIconRes: Int = R.drawable.ic_arrow_back,
    onBack: () -> Unit,
    title: String,
) {
    Surface(
        modifier =
            modifier.then(
                Modifier
                    .height(BACK_BUTTON_TOP_BAR_HEIGHT)
                    .fillMaxWidth(),
            ),
        color = MaterialTheme.colorScheme.background,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DefaultIconButton(
                modifier = Modifier.size(44.dp),
                iconRes = backIconRes,
                tint = Color.Black,
                onClick = onBack,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = title,
                fontSize = 22.sp,
            )
        }
    }
}

private val BACK_BUTTON_TOP_BAR_HEIGHT = 64.dp

@Preview
@Composable
private fun BackButtonTopBarPreview() {
    ShoppingCartTheme {
        BackButtonTopBar(
            title = stringResource(id = R.string.product_detail_title),
            onBack = {},
        )
    }
}
