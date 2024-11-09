package nextstep.shoppingcart.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductUiModel(
    val product: Product,
    val quantity: Int,
) : Parcelable
