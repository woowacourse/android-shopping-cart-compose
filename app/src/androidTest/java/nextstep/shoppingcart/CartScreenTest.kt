package nextstep.shoppingcart

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.shoppingcart.domain.CartProductRepository
import nextstep.shoppingcart.domain.Product
import nextstep.shoppingcart.ui.cart.CartScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CartScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        if (CartProductRepository.cartItems.isEmpty()) {
            CartProductRepository.addItem(
                Product(
                    id = 0,
                    name = "레아 사랑해요",
                    imageUrl = "히히",
                    price = 1004,
                ),
            )
        }
        composeTestRule.setContent {
            CartScreen()
        }
    }

    @Test
    fun `수량_증가_버튼을_누르면_상품의_수량이_수량_단위만큼_증가한다`() {
        // given
        val cartItem = CartProductRepository.cartItems.first()
        // when
        composeTestRule.onAllNodesWithContentDescription(
            "수량 증가 버튼",
        ).onFirst().performClick()

        // then
        composeTestRule.onNodeWithText(
            (cartItem.quantity.currentValue + cartItem.countInterval).toString(),
        ).isDisplayed()
    }

    @Test
    fun `수량_감소_버튼을_누르면_상품의_수량이_수량_단위만큼_감소한다`()  {
        // given
        val cartItem = CartProductRepository.cartItems.first()

        // when
        composeTestRule.onAllNodesWithContentDescription(
            "수량 감소 버튼",
        ).onFirst().performClick()

        // then
        composeTestRule.onNodeWithText(
            (cartItem.quantity.currentValue - cartItem.countInterval).toString(),
        ).isDisplayed()
    }

    @Test
    fun `상품의_수량을_증가시키면_변경된_가격이_버튼에_표시된다`()  {
        // given
        val cartItem = CartProductRepository.cartItems.first()
        val previousPrice = CartProductRepository.totalPrice()
        composeTestRule.onNodeWithText(
            "주문하기(${previousPrice}원)",
        ).isDisplayed()

        // when
        composeTestRule.onAllNodesWithContentDescription(
            "수량 증가 버튼",
        ).onFirst().performClick()

        // then
        composeTestRule.onNodeWithText(
            "주문하기(${previousPrice + cartItem.product.price}원)",
        ).isDisplayed()
    }
}
