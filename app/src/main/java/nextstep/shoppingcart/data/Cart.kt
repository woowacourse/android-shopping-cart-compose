package nextstep.shoppingcart.data

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import nextstep.shoppingcart.domain.model.CartItem
import nextstep.shoppingcart.domain.model.Product

object Cart {
    val items: SnapshotStateList<CartItem> = emptyList<CartItem>().toMutableStateList()

    val totalPrice = mutableIntStateOf(0)

    fun addOne(product: Product) {
        val item = items.find { it.product == product }
        if (item != null) {
            val index = items.indexOf(item)
            items[index] = item.copy(count = item.count + 1)
        } else {
            items.add(CartItem(product = product, count = 1))
        }
        updateState()
    }

    fun removeOne(product: Product) {
        val item = items.find { it.product == product }
        if (item != null) {
            if (item.count > 1) {
                val index = items.indexOf(item)
                items[index] = item.copy(count = item.count - 1)
            } else {
                items.remove(item)
            }
            updateState()
        }
    }

    fun removeAll(product: Product) {
        items.removeAll { it.product == product }
        updateState()
    }

    private fun updateState() {
        totalPrice.value = items.sumOf { it.product.price * it.count }
    }
}
