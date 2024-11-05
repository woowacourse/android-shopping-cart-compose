package nextstep.shoppingcart.ui.shoppingcart

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.shoppingcart.data.repository.DefaultShoppingCartRepository
import nextstep.shoppingcart.domain.model.Product
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShoppingCartScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val productA =
        Product(
            id = 0L,
            imageUrl =
                "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                    "%2FMjAyNDAyMjNfMjkg%2FMDAxNzA4NjE1NTg1ODg5.ZFPHZ3Q2HzH7GcYA1_Jl0lsIdvAnzUF2h6Qd6bgDLHkg." +
                    "_7ffkgE45HXRVgX2Bywc3B320_tuatBww5y1hS4xjWQg.JPEG%2FIMG_5278.jpg&type=sc960_832",
            name = "대전 장인약과",
            price = 12000,
        )

    private val productB =
        Product(
            id = 1L,
            imageUrl =
                "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                    "%2FMjAyNDAxMDVfNjYg%2FMDAxNzA0NDU0MTYwMTAx.4pxrLnIdvFp8KDGAnGkbl8zHo5Mcn0d-yD7pzToeiSsg." +
                    "lF4rd6908c0j_7kfxBr_u4MSdjq73RkhzKfRk7Z6VUMg.JPEG.rbxod123%2F1704454160034.jpg&type=sc960_832",
            name = "라라스윗 바닐라",
            price = 15000,
        )

    @Before
    fun setUp() {
        DefaultShoppingCartRepository.removeProduct(product = productA)
        DefaultShoppingCartRepository.removeProduct(product = productB)

        DefaultShoppingCartRepository.addProduct(product = productA)
        DefaultShoppingCartRepository.addProduct(product = productA)
        DefaultShoppingCartRepository.addProduct(product = productB)

        composeTestRule.setContent {
            ShoppingCartScreen(navigateToBack = {})
        }
    }

    @Test
    fun 담긴_상품_가격의_총합이_노출된다() {
        composeTestRule
            .onNodeWithText("주문하기(39,000원)")
            .assertExists()
    }

    @Test
    fun 담긴_상품을_제거할_수_있다() {
        composeTestRule.onAllNodesWithContentDescription("장바구니 상품 삭제").onFirst()
            .performClick()

        composeTestRule.onNodeWithText(productA.name)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("주문하기(15,000원)")
            .assertExists()
    }

    @Test
    fun 담긴_상품의_수량을_증가시키면_상품_가격에_반영된다() {
        composeTestRule.onAllNodesWithContentDescription("수량 증가").onFirst()
            .performClick()

        composeTestRule
            .onNodeWithText("36,000원")
            .assertExists()

        composeTestRule
            .onNodeWithText("주문하기(51,000원)")
            .assertExists()
    }

    @Test
    fun 담긴_상품의_수량을_감소시키면_상품_가격에_반영된다() {
        composeTestRule.onAllNodesWithContentDescription("수량 감소").onFirst()
            .performClick()

        composeTestRule
            .onNodeWithText("12,000원")
            .assertExists()

        composeTestRule
            .onNodeWithText("주문하기(27,000원)")
            .assertExists()
    }

    @Test
    fun 담긴_상품의_수량을_1보다_적게_하면_상품이_삭제된다() {
        composeTestRule.onAllNodesWithContentDescription("수량 감소").onFirst()
            .performClick()
        composeTestRule.onAllNodesWithContentDescription("수량 감소").onFirst()
            .performClick()

        composeTestRule.onNodeWithText(productA.name)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("주문하기(15,000원)")
            .assertExists()
    }
}
