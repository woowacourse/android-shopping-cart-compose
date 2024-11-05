package nextstep.shoppingcart.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShoppingCartProduct(
    val product: Product,
    val quantity: Int,
) : Parcelable {
    val totalPrice: Int get() = product.price * quantity

    fun plusQuantity(): ShoppingCartProduct {
        return copy(quantity = quantity + OFFSET_QUANTITY)
    }

    fun minusQuantity(): ShoppingCartProduct {
        return copy(quantity = (quantity - OFFSET_QUANTITY).coerceAtLeast(MIN_QUANTITY))
    }

    companion object {
        const val OFFSET_QUANTITY = 1
        const val MIN_QUANTITY = 1
    }
}
