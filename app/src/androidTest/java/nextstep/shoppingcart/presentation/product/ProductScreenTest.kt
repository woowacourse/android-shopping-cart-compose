package nextstep.shoppingcart.presentation.product

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.shoppingcart.presentation.product.model.ProductUiModel
import nextstep.shoppingcart.presentation.product.model.ProductUiState
import org.junit.Rule
import org.junit.Test

class ProductScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_display_ProductScreen() {
        composeTestRule
            .setContent {
                ProductScreen(
                    productState = ProductUiState.Empty,
                    onItemClick = {},
                    onCartClick = {},
                    onProductPlus = {},
                    onProductMinus = {},
                )
            }

        composeTestRule
            .onNodeWithText("상품 목록")
            .assertIsDisplayed()
    }

    @Test
    fun test_display_Product() {
        composeTestRule
            .setContent {
                ProductScreen(
                    productState = ProductUiState.Success(
                        listOf(
                            ProductUiModel(
                                id = 1L,
                                imageUrl = "testUrl",
                                name = "오둥이",
                                price = 1_000,
                                0
                            )
                        )
                    ),
                    onItemClick = {},
                    onCartClick = {},
                    onProductPlus = {},
                    onProductMinus = {},
                )
            }
        composeTestRule
            .onNodeWithText("오둥이")
            .assertIsDisplayed()
    }

    @Test
    fun test_display_Empty_Content() {
        composeTestRule
            .setContent {
                ProductScreen(
                    productState = ProductUiState.Empty,
                    onItemClick = {},
                    onCartClick = {},
                    onProductPlus = {},
                    onProductMinus = {},
                )
            }
        composeTestRule
            .onNodeWithText("상품이 없습니다.")
            .assertIsDisplayed()
    }
}