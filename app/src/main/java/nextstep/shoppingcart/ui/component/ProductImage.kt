package nextstep.shoppingcart.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

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

@Composable
@Preview(showBackground = true)
private fun ProductImagePreview() {
    ShoppingCartTheme {
        ProductImage(
            product =
            Product(
                id = 0L,
                imageUrl =
                "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                        "%2FMjAyNDAyMjNfMjkg%2FMDAxNzA4NjE1NTg1ODg5.ZFPHZ3Q2HzH7GcYA1_Jl0lsIdvAnzUF2h6Qd6bgDLHkg." +
                        "_7ffkgE45HXRVgX2Bywc3B320_tuatBww5y1hS4xjWQg.JPEG%2FIMG_5278.jpg&type=sc960_832",
                name = "대전 장인약과",
                price = 12000,
            ),
            contentScale = ContentScale.Crop,
        )
    }
}
