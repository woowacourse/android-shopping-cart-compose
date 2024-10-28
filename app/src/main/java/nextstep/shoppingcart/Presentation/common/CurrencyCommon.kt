package nextstep.shoppingcart.Presentation.common

import android.content.Context
import nextstep.signup.R
import java.text.NumberFormat
import java.util.Locale

fun Int.currency(context: Context): String =
    when (Locale.getDefault().country) {
        Locale.KOREA.country -> context.getString(R.string.price_format_kor, this)
        else -> NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this)
    }
