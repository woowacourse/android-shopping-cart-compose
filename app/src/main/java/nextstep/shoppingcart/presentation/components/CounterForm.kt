package nextstep.shoppingcart.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R

@Composable
fun CounterForm(
    count: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier =
            Modifier
                .clickable { onDecrease() }
        ) {
            Text(
                text = stringResource(R.string.minus_mark),
                style =
                TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(12.dp)
            )
        }
        Text(
            text = count.toString(),
            modifier = Modifier.padding(horizontal = 8.dp),
            style = TextStyle(fontSize = 22.sp)
        )
        Box(
            modifier =
            Modifier
                .clickable { onIncrease() }
        ) {
            Text(
                text = stringResource(R.string.plus_mark),
                style =
                TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CounterFormPreview() {
    CounterForm(
        count = 1,
        onIncrease = {},
        onDecrease = {}
    )
}
