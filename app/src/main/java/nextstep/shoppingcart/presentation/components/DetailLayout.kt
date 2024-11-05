package nextstep.shoppingcart.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import nextstep.shoppingcart.domain.model.Price
import nextstep.shoppingcart.domain.model.Product

@Composable
fun DetailLayout(
    navigation: () -> Unit = {},
    action: () -> Unit = {},
    product: Product,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        bottomBar = {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF2196F3))
                        .padding(16.dp)
                        .clickable { action() },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "장바구니 담기",
                    fontSize = 20.sp,
                    style =
                        TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        ),
                )
            }
        },
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            TopBarWithNavigation(
                name = "상품 상세",
                navigation = { navigation() },
                modifier = modifier,
            )
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
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
                        fontWeight = FontWeight.Bold,
                    ),
            )

            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier =
                    Modifier
                        .fillMaxWidth(),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "금액",
                    fontSize = 20.sp,
                    modifier =
                        Modifier
                            .padding(18.dp)
                            .align(Alignment.CenterVertically),
                )
                Text(
                    text = Price(product.price).format(),
                    fontSize = 20.sp,
                    modifier =
                        Modifier
                            .padding(18.dp)
                            .align(Alignment.CenterVertically),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailLayoutPreview() {
    DetailLayout(product = Product.NULL_PRODUCT)
}
