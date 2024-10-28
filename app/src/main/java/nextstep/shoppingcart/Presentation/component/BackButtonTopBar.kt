package nextstep.shoppingcart.Presentation.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nextstep.signup.R

@Composable
fun BackButtonTopBar(
    modifier: Modifier = Modifier,
    @DrawableRes backIconRes: Int = R.drawable.ic_arrow_back,
    onBack: () -> Unit,
    @StringRes titleRes: Int,
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
        ) {
            DefaultIconButton(
                modifier = Modifier.size(24.dp),
                iconRes = backIconRes,
                tint = Color.Black,
                onClick = onBack,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = stringResource(id = titleRes))
        }
    }
}

private val BACK_BUTTON_TOP_BAR_HEIGHT = 64.dp
