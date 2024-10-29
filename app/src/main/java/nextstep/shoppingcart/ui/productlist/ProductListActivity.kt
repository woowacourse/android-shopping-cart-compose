package nextstep.shoppingcart.ui.productlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

class ProductListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingCartTheme {
                ProductListScreen()
            }
        }
    }
}

@Composable
fun ProductListScreen() {
    Scaffold(
        topBar = {
            ProductListTopBar({})
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        ProductList(
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductListScreenPreview() {
    ShoppingCartTheme {
        ProductListScreen()
    }
}
