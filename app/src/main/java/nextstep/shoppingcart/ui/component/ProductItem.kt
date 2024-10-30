package nextstep.shoppingcart.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.products
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier.clickable {
                onClick()
            },
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
            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = stringResource(R.string.price_format, product.price),
            modifier = Modifier.padding(horizontal = 4.dp),
            fontSize = 16.sp,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProductItemPreview() {
    ShoppingCartTheme {
        ProductItem(product = products.first(), onClick = {})
    }
}
