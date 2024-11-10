package nextstep.shoppingcart.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import nextstep.shoppingcart.domain.model.CartItem
import nextstep.shoppingcart.domain.model.Price
import nextstep.shoppingcart.domain.model.Product
import nextstep.signup.R

@Composable
fun ShoppingCartScreen(
    items: List<CartItem> = emptyList(),
    onIncrease: (CartItem) -> Unit = {},
    onDecrease: (CartItem) -> Unit = {},
    onClear: (CartItem) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val totalPrice = items.sumOf { it.product.price * it.count }

    Scaffold(
        bottomBar = {
            Box(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2196F3))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.order, Price(totalPrice).format()),
                    fontSize = 20.sp,
                    style =
                    TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(items) { item ->
                    CartItemView(
                        item = item,
                        onIncrease = onIncrease,
                        onDecrease = onDecrease,
                        onClear = onClear
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CartItemView(
    item: CartItem,
    onIncrease: (CartItem) -> Unit = {},
    onDecrease: (CartItem) -> Unit = {},
    onClear: (CartItem) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp)),
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
                AsyncImage(
                    model = item.product.imageUrl,
                    contentDescription = item.product.name,
                    modifier =
                    Modifier
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
                Product(
                    id = 1L,
                    name = "[든든] 동원 스위트콘",
                    price = 42200,
                    imageUrl = "https://thumbnail7"
                ),
                count = 1
            ),
            CartItem(
                Product(
                    id = 2L,
                    name = "PET보틀-원형(500ml)",
                    price = 84400,
                    imageUrl = ""
                ),
                count = 2
            )
        )
    ShoppingCartScreen(items)
}
