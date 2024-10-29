package nextstep.shoppingcart.ui.productdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.domain.Product
import nextstep.shoppingcart.domain.ProductRepository
import nextstep.shoppingcart.ui.theme.Gray10
import nextstep.shoppingcart.ui.theme.Gray20
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.shoppingcart.ui.theme.Typography
import nextstep.signup.R

@Composable
fun ProductDetailContent(
    product: Product,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = stringResource(R.string.product_detail_image_desc),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

        Text(
            text = product.name,
            color = Gray20,
            modifier = Modifier.padding(18.dp),
            style = Typography.titleLarge
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = Gray10
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.product_detail_price),
                color = Gray20,
                modifier = Modifier.padding(18.dp),
                style = Typography.bodyMedium,
            )
            Text(
                text = stringResource(R.string.product_price_format, product.price),
                color = Gray20,
                style = Typography.bodyMedium,
                modifier = Modifier.padding(18.dp)
            )
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
private fun ProductDetailContentPreview() {
    ShoppingCartTheme {
        ProductDetailContent(
            ProductRepository.dummy.first()
        )
    }
}