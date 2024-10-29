package nextstep.shoppingcart.ui.productdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.ui.component.BackNavigationTopAppBar
import nextstep.shoppingcart.ui.productlist.model.products
import nextstep.shoppingcart.ui.theme.Blue50
import nextstep.signup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Long,
    navigateToBack: () -> Unit,
    navigateToShoppingCart: () -> Unit,
) {
    val product =
        products.find { product ->
            product.id == productId
        } ?: return

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavigationTopAppBar(
                title = stringResource(R.string.product_list),
                navigateToBack = navigateToBack,
            )
        },
    ) { contentPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = product.name,
                modifier = Modifier.padding(18.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            HorizontalDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(R.string.price),
                    modifier = Modifier.padding(18.dp),
                    fontSize = 20.sp,
                )

                Text(
                    text = stringResource(R.string.price_format, product.price),
                    modifier = Modifier.padding(18.dp),
                    fontSize = 20.sp,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    navigateToShoppingCart()
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Blue50),
            ) {
                Text(
                    text = stringResource(R.string.put_shopping_cart),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductDetailScreenPreview() {
    ProductDetailScreen(productId = 0L, navigateToBack = {}, navigateToShoppingCart = {})
}
