package nextstep.shoppingcart.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import nextstep.shoppingcart.domain.model.Price
import nextstep.shoppingcart.domain.model.Product

@Composable
fun ProductList(
    items: List<Product>,
    onItemClick: (Long) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
    ) {
        items(items) { item ->
            ProductItem(
                product = item,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    onItemClick: (Long) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(product.id) }
            .padding(top = 20.dp, start = 6.dp, end = 6.dp)
    ) {
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
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp),
            style =
            TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = Price(product.price).format(),
            style =
            TextStyle(
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyListPreview() {
    ProductList(
        items =
        listOf(
            Product(
                id = 1,
                name = "name1",
                price = 1000,
                imageUrl = "https://thumbnail7.coupangcdn.com/thumbnails/remote/292x292ex/image/retail/images/1263603036762773-f6291401-9c64-4944-8189-86e5aead6049.png"
            ),
            Product(
                id = 2,
                name = "name2",
                price = 2000,
                imageUrl = "https://thumbnail7.coupangcdn.com/thumbnails/remote/292x292ex/image/vendor_inventory/e294/d78edd81a8f38ae32984e9ad3393a840bd9ccdc91161838a8036f4d90434.jpg"
            ),
            Product(
                id = 3,
                name = "name3",
                price = 3000,
                imageUrl = "https://thumbnail7.coupangcdn.com/thumbnails/remote/292x292ex/image/1025_amir_coupang_oct_80k/e308/9c53df34079cb2e6a8123f93355a796ae18b7979bc61bd360da0793314af.jpg"
            )
        )
    )
}
