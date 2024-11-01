package nextstep.shoppingcart.ui.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.domain.CartProduct
import nextstep.shoppingcart.ui.component.CountUpdateButton
import nextstep.shoppingcart.ui.theme.Gray10
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.shoppingcart.ui.theme.Typography
import nextstep.signup.R

@Composable
fun CartItem(
    cartProduct: CartProduct,
    onCountMinus: () -> Unit,
    onCountPlus: () -> Unit,
    onItemDelete: () -> Unit,
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, Gray10),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 6.dp, top = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    text = cartProduct.product.name,
                    style = Typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                IconButton(
                    modifier =
                    Modifier
                        .height(48.dp)
                        .width(48.dp),
                    onClick = onItemDelete,
                ) {
                    Icon(
                        modifier =
                        Modifier
                            .height(24.dp)
                            .width(24.dp),
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.product_list_cart_button_desc),
                    )
                }
            }

            Row(
                modifier =
                Modifier
                    .padding(horizontal = 18.dp)
                    .padding(bottom = 18.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                AsyncImage(
                    model = cartProduct.product.imageUrl,
                    modifier =
                    Modifier
                        .width(136.dp)
                        .height(84.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(R.string.cart_product_image_desc),
                )

                Column(
                    modifier = Modifier.align(Alignment.Bottom),
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text =
                            stringResource(
                                R.string.product_price_format,
                                cartProduct.totalPrice,
                            ),
                    )

                    CountUpdateButton(
                        count = cartProduct.quantity.currentValue,
                        onCountMinus = onCountMinus,
                        onCountPlus = onCountPlus,
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun CartItemPreview() {
    ShoppingCartTheme {
        CartItem(CartProduct.dummy, { }, {}, {})
    }
}
