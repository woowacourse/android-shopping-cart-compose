package nextstep.shoppingcart.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackNavigationTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    TopAppBar(title = {
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }, navigationIcon = {
        IconButton(
            onClick = {
                navigateToBack()
            },
            modifier = modifier,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.shopping_cart),
            )
        }
    })
}
