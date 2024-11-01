package nextstep.shoppingcart.ui.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.shoppingcart.domain.CartProductRepository
import nextstep.shoppingcart.domain.Product
import nextstep.shoppingcart.domain.ProductRepository
import nextstep.shoppingcart.ui.component.BackNavigationTopBar
import nextstep.shoppingcart.ui.component.SubmitButton
import nextstep.shoppingcart.ui.productdetail.ProductDetailActivity.Companion.EXTRA_PRODUCT_ID
import nextstep.shoppingcart.ui.productdetail.ProductDetailActivity.Companion.INVALID_PRODUCT_ID
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.shoppingcart.ui.util.findActivity
import nextstep.signup.R

class ProductDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingCartTheme {
                ProductDetailScreen()
            }
        }
    }

    companion object {
        const val EXTRA_PRODUCT_ID = "productId"
        const val INVALID_PRODUCT_ID = -1L

        fun newIntent(
            context: Context,
            productId: Long,
        ): Intent =
            Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(EXTRA_PRODUCT_ID, productId)
            }
    }
}

@Composable
fun ProductDetailScreen() {
    val activity = LocalContext.current.findActivity()

    val product: Product =
        ProductRepository.productById(
            activity.intent.getLongExtra(
                EXTRA_PRODUCT_ID,
                INVALID_PRODUCT_ID,
            ),
        )

    Scaffold(
        topBar = {
            BackNavigationTopBar(
                title = stringResource(R.string.product_detail_title),
                onClickNavigationIcon = {
                    activity.finish()
                },
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->

        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            ProductDetailContent(
                product,
            )

            SubmitButton(
                label = stringResource(R.string.product_detail_cart_button),
                onClick = {
                    CartProductRepository.addItem(product)
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun ProductDetailScreenPreview() {
    ShoppingCartTheme {
        ProductDetailScreen()
    }
}
