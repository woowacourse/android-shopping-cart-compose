package nextstep.shoppingcart.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShoppingCartProducts(
    val items: List<ShoppingCartProduct>,
) : Parcelable {
    val totalPrice: Int
        get() =
            items.sumOf { item ->
                item.totalPrice
            }
}
