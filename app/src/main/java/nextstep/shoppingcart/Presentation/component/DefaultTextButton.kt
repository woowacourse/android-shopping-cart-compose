package nextstep.shoppingcart.Presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.Presentation.common.currency
import nextstep.shoppingcart.Presentation.ui.theme.Blue50
import nextstep.shoppingcart.Presentation.ui.theme.ShoppingCartTheme
import nextstep.shoppingcart.data.repository.CartRepository
import nextstep.signup.R

@Composable
fun DefaultTextButton(
    text: String,
    style: TextStyle,
    colors: ButtonColors,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        colors = colors,
        shape = shape,
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = text,
            style = style,
        )
    }
}

@Preview
@Composable
private fun DefaultTextButtonPreview() {
    val context = LocalContext.current
    ShoppingCartTheme {
        DefaultTextButton(
            text =
                stringResource(R.string.order_title).format(
                    CartRepository.totalPrice.currency(context = context),
                ),
            style =
                TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                ),
            colors = ButtonDefaults.buttonColors(Blue50),
            modifier = Modifier.fillMaxWidth().height(54.dp),
        )
    }
}
