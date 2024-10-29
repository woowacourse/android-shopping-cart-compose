package nextstep.shoppingcart.ui.productlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import nextstep.shoppingcart.domain.Product
import nextstep.shoppingcart.domain.ProductRepository
import nextstep.shoppingcart.ui.theme.Typography
import nextstep.signup.R

@Composable
fun ProductListItem(
    product: Product,
    onClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .clickable {
                    onClick(product)
                },
    ) {
        AsyncImage(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
            model = product.imageUrl,
            contentDescription = stringResource(R.string.product_list_image_desc, product.name),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = product.name,
            style = Typography.titleSmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Text(
            text = stringResource(R.string.product_price_format, product.price),
            style = Typography.bodySmall,
            overflow = TextOverflow.Visible,
            maxLines = 1,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductListItemPreview() {
    ProductListItem(ProductRepository.dummy.first(), {})
}
