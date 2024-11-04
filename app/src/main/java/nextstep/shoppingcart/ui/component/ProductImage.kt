package nextstep.shoppingcart.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import nextstep.shoppingcart.domain.model.Product

@Composable
fun ProductImage(
    product: Product,
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
) {
    AsyncImage(
        model = product.imageUrl,
        contentDescription = product.name,
        modifier = modifier,
        contentScale = contentScale,
    )
}