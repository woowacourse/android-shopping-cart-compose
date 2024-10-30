package nextstep.shoppingcart.ui.productdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.ui.component.BackNavigationTopAppBar
import nextstep.shoppingcart.ui.component.RectangleButton
import nextstep.shoppingcart.ui.productlist.model.products
import nextstep.signup.R

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

            RectangleButton(
                text = stringResource(R.string.put_shopping_cart),
                onClick = navigateToShoppingCart,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductDetailScreenPreview() {
    ProductDetailScreen(productId = 0L, navigateToBack = {}, navigateToShoppingCart = {})
}
