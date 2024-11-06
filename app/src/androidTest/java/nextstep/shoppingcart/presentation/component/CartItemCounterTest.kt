package nextstep.shoppingcart.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import nextstep.shoppingcart.data.model.CartItem
import nextstep.shoppingcart.data.model.Product
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CartItemCounterTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var product: Product
    lateinit var cartItem: CartItem

    @Before
    fun setUp() {
        product = Product(id = 1, name = "product", price = 1000, imageUrl = "")
        cartItem = CartItem(product = product, count = INIT_COUNT)

        composeTestRule.setContent {
            CartItemCounter(
                cartItem = cartItem,
                onItemIncremented = { _ ->
                    cartItem = cartItem.copy(count = cartItem.count + 1)
                },
                onItemDecremented = { _ ->
                    cartItem = cartItem.copy(count = cartItem.count - 1)
                },
            )
        }
    }

    @Test
    fun `클릭시_수량이_1_증가한다`() {
        // given & when
        composeTestRule.onNodeWithContentDescription("수량 추가").performClick()

        // then
        assertEquals(INIT_COUNT + 1, cartItem.count)
    }

    @Test
    fun 클릭시_수량이_1_감소한다() {
        // given & when
        composeTestRule.onNodeWithContentDescription("수량 감소").performClick()

        // then
        assertEquals(INIT_COUNT - 1, cartItem.count)
    }

    companion object {
        const val INIT_COUNT = 1
    }
}
