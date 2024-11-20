package nextstep.shoppingcart.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.signup.R

@Composable
fun BottomBar(
    text: String = stringResource(R.string.add_to_cart),
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            style =
            TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    BottomBar()
}
