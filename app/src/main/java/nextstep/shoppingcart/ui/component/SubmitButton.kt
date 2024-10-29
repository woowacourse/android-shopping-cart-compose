package nextstep.shoppingcart.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import nextstep.shoppingcart.ui.theme.Blue50
import nextstep.shoppingcart.ui.theme.Gray10
import nextstep.shoppingcart.ui.theme.Gray50
import nextstep.shoppingcart.ui.theme.Typography
import nextstep.signup.R

@Composable
fun SubmitButton(
    modifier: Modifier
) {
    Button(
        shape = RectangleShape,
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonColors(
            containerColor = Blue50,
            contentColor = Color.White,
            disabledContentColor = Gray10,
            disabledContainerColor = Gray50
        ),
        onClick = {}
    ) {
        Text(
            text = stringResource(R.string.product_detail_cart_button),
            style = Typography.labelMedium
        )
    }
}