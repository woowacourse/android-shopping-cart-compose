package nextstep.shoppingcart.presentation.ui.shoppingcart

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.shoppingcart.data.model.Product
import nextstep.shoppingcart.data.repository.CartRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat
import java.util.Locale

class ShoppingCartScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var product: Product
    private lateinit var actions: List<ShoppingCartAction>

    @Before
    fun setUp() {
        product = Product(id = 1, name = "product", price = 1000, imageUrl = "")
        CartRepository.addOne(product)
        actions = emptyList()
        composeTestRule.setContent {
            ShoppingCartScreen(onBack = {})
        }
    }

    @Test
    fun 총합_금액이_올바르게_표시된다() {
        // given & when
        val expectedTotal = CartRepository.items.first().totalPrice
        val formattedTotal = NumberFormat.getNumberInstance(Locale.KOREA).format(expectedTotal)

        // then
        composeTestRule
            .onNodeWithText("주문하기(${formattedTotal}원)")
            .assertExists()
    }

    @Test
    fun 상품을_제거할_수_있다() {
        // given & when
        val cartItem = CartRepository.items.first()
        composeTestRule
            .onNodeWithContentDescription("상품 제거")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText(cartItem.product.name)
            .assertDoesNotExist()
    }

    @Test
    fun 담긴_상품의_수량을_1보다_적게_하면_상품이_삭제된다() {
        // given & when
        val cartItem = CartRepository.items.first()
        composeTestRule
            .onNodeWithContentDescription("수량 감소")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText(cartItem.product.name)
            .assertDoesNotExist()
    }
}
