package nextstep.shoppingcart.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.shoppingcart.ui.theme.Typography
import nextstep.signup.R

@Composable
fun CountUpdateButton(
    count: Int,
    onCountMinus: () -> Unit,
    onCountPlus: () -> Unit,
) {
    Row {
        IconButton(
            modifier =
                Modifier
                    .width(42.dp)
                    .height(42.dp),
            onClick = onCountMinus,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_minus),
                contentDescription = stringResource(R.string.count_minus_desc),
            )
        }

        Text(
            text = count.toString(),
            style = Typography.displayMedium,
            modifier =
                Modifier
                    .wrapContentHeight()
                    .padding(horizontal = 12.dp)
                    .align(Alignment.CenterVertically),
        )

        IconButton(
            modifier =
                Modifier
                    .width(42.dp)
                    .height(42.dp),
            onClick = onCountPlus,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = stringResource(R.string.count_plus_desc),
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun CountUpdateButtonPreview() {
    CountUpdateButton(1, {}, {})
}
