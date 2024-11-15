package nextstep.shoppingcart.presentation.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import nextstep.shoppingcart.domain.model.Price
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.presentation.components.BottomBar
import nextstep.shoppingcart.presentation.components.topbars.TopBarWithNavigation
import nextstep.shoppingcart.ui.theme.Blue50
import nextstep.signup.R

@Composable
fun DetailScreen(
    navigation: () -> Unit = {},
    action: () -> Unit = {},
    product: Product,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBar(
                text = stringResource(R.string.add_to_cart),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Blue50)
                    .padding(16.dp)
                    .clickable { action() }
            )
        }
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            TopBarWithNavigation(
                name = stringResource(R.string.title_product_detail),
                navigation = { navigation() },
                modifier = modifier
            )
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            Text(
                text = product.name,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                style =
                TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier =
                Modifier
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.price),
                    fontSize = 20.sp,
                    modifier =
                    Modifier
                        .padding(18.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = Price(product.price).format(),
                    fontSize = 20.sp,
                    modifier =
                    Modifier
                        .padding(18.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DetailLayoutPreview() {
    DetailScreen(product = Product.NULL_PRODUCT)
}
