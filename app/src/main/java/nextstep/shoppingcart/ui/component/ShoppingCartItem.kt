package nextstep.shoppingcart.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.domain.model.ShoppingCartProduct
import nextstep.shoppingcart.domain.model.products
import nextstep.shoppingcart.ui.shoppingcart.ShoppingCartAction
import nextstep.shoppingcart.ui.shoppingcart.ShoppingCartAction.MinusQuantity
import nextstep.shoppingcart.ui.shoppingcart.ShoppingCartAction.PlusQuantity
import nextstep.shoppingcart.ui.shoppingcart.ShoppingCartAction.RemoveProduct
import nextstep.shoppingcart.ui.theme.Gray10
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun ShoppingCartItem(
    shoppingCartProduct: ShoppingCartProduct,
    action: (ShoppingCartAction) -> Unit,
) {
    OutlinedCard(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 16.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Gray10),
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = shoppingCartProduct.product.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )

            IconButton(
                onClick = {
                    action(RemoveProduct(product = shoppingCartProduct.product))
                },
                modifier = Modifier.size(24.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = stringResource(R.string.remove_shopping_cart_item),
                )
            }
        }

        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    start = 18.dp,
                    end = 18.dp,
                    top = 6.dp,
                    bottom = 18.dp,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ProductImage(
                product = shoppingCartProduct.product,
                modifier = Modifier.size(width = 136.dp, height = 84.dp),
                contentScale = ContentScale.FillWidth
            )

            Column(modifier = Modifier.align(Alignment.Bottom)) {
                Text(
                    text = stringResource(R.string.price_format, shoppingCartProduct.totalPrice),
                    modifier =
                    Modifier
                        .align(Alignment.End),
                    fontSize = 16.sp,
                )

                QuantityControl(
                    modifier = Modifier.size(42.dp),
                    quantity = shoppingCartProduct.quantity,
                    minusQuantity = { action(MinusQuantity(product = shoppingCartProduct.product)) },
                    plusQuantity = { action(PlusQuantity(product = shoppingCartProduct.product)) },
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShoppingCartItemPreview() {
    ShoppingCartTheme {
        ShoppingCartItem(
            shoppingCartProduct =
            ShoppingCartProduct(
                products.first(),
                quantity = 2,
            ),
            action = {},
        )
    }
}
