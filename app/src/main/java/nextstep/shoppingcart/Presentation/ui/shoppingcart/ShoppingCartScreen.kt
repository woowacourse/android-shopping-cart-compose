package nextstep.shoppingcart.presentation.ui.shoppingcart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.data.model.CartItem
import nextstep.shoppingcart.data.repository.CartRepository
import nextstep.shoppingcart.data.repository.ProductRepository
import nextstep.shoppingcart.presentation.common.currency
import nextstep.shoppingcart.presentation.component.BackButtonTopBar
import nextstep.shoppingcart.presentation.component.CartItemCounter
import nextstep.shoppingcart.presentation.component.DefaultTextButton
import nextstep.shoppingcart.presentation.ui.theme.Blue50
import nextstep.shoppingcart.presentation.ui.theme.Gray10
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun ShoppingCartScreen(onBack: () -> Unit) {
    var cartItems by rememberSaveable { mutableStateOf(CartRepository.items) }

    Scaffold(
        topBar = {
            BackButtonTopBar(
                title = stringResource(id = R.string.shopping_cart_title),
                onBack = onBack,
            )
        },
    ) { paddingValues ->
        ShoppingCartContent(
            cartItems = cartItems,
            modifier = Modifier.padding(paddingValues = paddingValues),
            action = { action ->
                when (action) {
                    is ShoppingCartAction.OnItemIncrement -> CartRepository.addOne(action.product)
                    is ShoppingCartAction.OnItemDecrement -> CartRepository.removeOne(action.product)
                    is ShoppingCartAction.OnItemRemove -> CartRepository.removeAll(action.product)
                }
                cartItems = CartRepository.items
            },
        )
    }
}

@Composable
fun ShoppingCartContent(
    cartItems: List<CartItem>,
    modifier: Modifier = Modifier,
    action: (ShoppingCartAction) -> Unit,
) {
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize()) {
        ShoppingCartLazyColumn(
            cartItems = cartItems,
            action = action,
        )

        DefaultTextButton(
            text =
                stringResource(R.string.order_title).format(
                    CartRepository.totalPrice.currency(context = context),
                ),
            style =
                TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                ),
            colors = ButtonDefaults.buttonColors(Blue50),
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth()
                    .height(54.dp),
        )
    }
}

@Composable
private fun ShoppingCartLazyColumn(
    cartItems: List<CartItem>,
    modifier: Modifier = Modifier,
    action: (ShoppingCartAction) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(all = 18.dp),
    ) {
        items(
            key = { cartItem -> cartItem.product.id },
            items = cartItems,
        ) { cartItem ->
            ShoppingCartItem(
                cartItem = cartItem,
                action = action,
            )
        }
        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@Composable
private fun ShoppingCartItem(
    cartItem: CartItem,
    action: (ShoppingCartAction) -> Unit,
) {
    OutlinedCard(
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Gray10),
        modifier =
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(bottom = 16.dp),
    ) {
        ShoppingCartItemContent(
            cartItem = cartItem,
            action = action,
        )
    }
}

@Composable
private fun ShoppingCartItemContent(
    cartItem: CartItem,
    action: (ShoppingCartAction) -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(all = 18.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = cartItem.product.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Icon(
                modifier =
                    Modifier
                        .size(24.dp)
                        .clickable { action(ShoppingCartAction.OnItemRemove(cartItem.product)) },
                painter = painterResource(R.drawable.ic_close),
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AsyncImage(
                modifier =
                    Modifier
                        .weight(1f)
                        .height(84.dp),
                model = cartItem.product.imageUrl,
                contentDescription = null,
                error = painterResource(R.drawable.ic_launcher_background),
            )

            Spacer(modifier = Modifier.width(26.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = AbsoluteAlignment.Right,
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = cartItem.totalPrice.currency(context),
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(11.dp))
                CartItemCounter(
                    cartItem = cartItem,
                    onItemIncremented = {
                        action(ShoppingCartAction.OnItemIncrement(cartItem.product))
                    },
                    onItemDecremented = {
                        action(ShoppingCartAction.OnItemDecrement(cartItem.product))
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun ShoppingCartScreenPreview() {
    ShoppingCartTheme {
        ShoppingCartScreen(onBack = {})
    }
}

@Preview
@Composable
private fun ShoppingCartItemPreview() {
    val product = ProductRepository.getProducts().first()
    val cartItem = CartItem(product, 0)
    ShoppingCartTheme {
        ShoppingCartItem(cartItem, {})
    }
}
