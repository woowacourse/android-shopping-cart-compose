package nextstep.shoppingcart.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.domain.model.CartItem
import nextstep.shoppingcart.domain.model.Price
import nextstep.shoppingcart.domain.model.Product

@Composable
fun CartItemList(
    items: List<CartItem> = emptyList(),
    onIncrease: (CartItem) -> Unit = {},
    onDecrease: (CartItem) -> Unit = {},
    onClear: (CartItem) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(50.dp)
    ) {
        items(
            items = items,
            key = { item ->
                item.id
            }
        ) { item ->
            CartItem(
                item = item,
                onIncrease = onIncrease,
                onDecrease = onDecrease,
                onClear = onClear,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp)),
            )
        }
    }
}


@Composable
private fun CartItem(
    item: CartItem,
    onIncrease: (CartItem) -> Unit = {},
    onDecrease: (CartItem) -> Unit = {},
    onClear: (CartItem) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = item.product.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style =
                    TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { onClear(item) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear"
                    )
                }
            }
            Row {
                ProductImage(
                    imageUrl = item.product.imageUrl,
                    contentDescription = item.product.name,
                    modifier = Modifier
                        .width(136.dp)
                        .height(84.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = Price(item.product.price).format(),
                        fontSize = 16.sp
                    )
                    CounterForm(
                        count = item.count,
                        onIncrease = { onIncrease(item) },
                        onDecrease = { onDecrease(item) }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ShoppingCartPreview() {
    val items =
        listOf(
            CartItem(
                id = 0L,
                Product(
                    id = 1L,
                    name = "[든든] 동원 스위트콘",
                    price = 42200,
                    imageUrl = "https://thumbnail7"
                ),
                count = 1
            ),
            CartItem(
                id = 1L,
                Product(
                    id = 2L,
                    name = "PET보틀-원형(500ml)",
                    price = 84400,
                    imageUrl = ""
                ),
                count = 2
            )
        )
    CartItemList(items)
}
