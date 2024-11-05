package nextstep.shoppingcart.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithAction(
    name: String = "상품목록",
    action: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = name,
                    style =
                        TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                )
            },
            modifier = modifier,
            actions = {
                IconButton(onClick = { action() }) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Shopping Cart",
                    )
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithNavigation(
    name: String = "상품 상세",
    navigation: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = name,
                    style =
                        TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                )
            },
            modifier = modifier,
            navigationIcon = {
                IconButton(
                    onClick = { navigation() },
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            },
        )
    }
}

@Preview
@Composable
fun BarLayoutPreview() {
    Column {
        TopBarWithAction()
        TopBarWithNavigation(name = "상품 상세")
    }
}
