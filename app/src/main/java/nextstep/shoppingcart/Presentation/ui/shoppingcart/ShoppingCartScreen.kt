package nextstep.shoppingcart.Presentation.ui.shoppingcart

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
import nextstep.shoppingcart.Presentation.common.currency
import nextstep.shoppingcart.Presentation.component.BackButtonTopBar
import nextstep.shoppingcart.Presentation.component.CartItemCounter
import nextstep.shoppingcart.Presentation.component.DefaultTextButton
import nextstep.shoppingcart.Presentation.ui.theme.Blue50
import nextstep.shoppingcart.Presentation.ui.theme.Gray10
import nextstep.shoppingcart.Presentation.ui.theme.ShoppingCartTheme
import nextstep.shoppingcart.data.model.CartItem
import nextstep.shoppingcart.data.repository.CartRepository
import nextstep.shoppingcart.data.repository.ProductRepository
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
            actions =
                CartItemActions(
                    onItemIncrement = { product ->
                        CartRepository.addOne(product)
                        cartItems = CartRepository.items
                    },
                    onItemDecrement = { product ->
                        CartRepository.removeOne(product)
                        cartItems = CartRepository.items
                    },
                    onItemRemove = { product ->
                        CartRepository.removeAll(product)
                        cartItems = CartRepository.items
                    },
                ),
        )
    }
}

@Composable
private fun ShoppingCartContent(
    cartItems: List<CartItem>,
    modifier: Modifier = Modifier,
    actions: CartItemActions,
) {
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize()) {
        ShoppingCartLazyColumn(
            cartItems = cartItems,
            actions = actions,
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
    actions: CartItemActions,
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
                actions = actions,
            )
        }
        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@Composable
private fun ShoppingCartItem(
    cartItem: CartItem,
    actions: CartItemActions,
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
            actions = actions,
        )
    }
}

@Composable
private fun ShoppingCartItemContent(
    cartItem: CartItem,
    actions: CartItemActions,
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
                        .clickable { actions.onItemRemove(cartItem.product) },
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
                    onItemIncremented = actions.onItemIncrement,
                    onItemDecremented = actions.onItemDecrement,
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
        ShoppingCartItem(cartItem, CartItemActions({}, {}, {}))
    }
}
