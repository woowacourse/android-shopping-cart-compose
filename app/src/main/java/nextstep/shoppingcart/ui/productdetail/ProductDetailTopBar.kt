package nextstep.shoppingcart.ui.productdetail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import nextstep.shoppingcart.ui.theme.Typography
import nextstep.signup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailTopBar(onClickNavigationIcon: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.product_detail_title),
                style = Typography.headlineMedium,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onClickNavigationIcon,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.product_detail_back_button_desc),
                )
            }
        },
    )
}
