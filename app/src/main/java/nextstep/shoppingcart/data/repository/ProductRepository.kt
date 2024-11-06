package nextstep.shoppingcart.data.repository

import nextstep.shoppingcart.data.model.Product

object ProductRepository {
    fun getProducts() = dummyProducts

    fun findProductById(id: Long): Product = dummyProducts.first { it.id == id }

    val dummyProducts =
        listOf(
            Product(
                id = 1L,
                name = "Wireless Earbuds",
                price = 29900,
                imageUrl = "https://resource.logitech.com/content/dam/logitech/en/products/video-conferencing/zone-true-wireless-earbuds/zone-true-wireless-earbuds-gallery-1-graphite.png",
            ),
            Product(
                id = 2L,
                name = "Smartphone Case",
                price = 12000,
                imageUrl = "https://cdn-stamplib.casetify.com/cms/image/b32f44adda092acc8c81a66acbd54610.jpg",
            ),
            Product(
                id = 3L,
                name = "Bluetooth Speaker",
                price = 45000,
                imageUrl = "https://image10.coupangcdn.com/image/vendor_inventory/9c94/59c4a046068687ad284718f729a631279e6963a79865f671801867845318.jpg",
            ),
            Product(
                id = 4L,
                name = "Smartwatch",
                price = 159000,
                imageUrl = "https://image6.coupangcdn.com/image/retail/images/100810448383053-76bc9b64-7262-411a-b91c-c01e495dcc86.jpg",
            ),
            Product(
                id = 5L,
                name = "Portable Charger",
                price = 22000,
                imageUrl = "https://astroboyedition.com/web/product/big/202408/d02f69eec456b924363714d08b10bb6e.png",
            ),
            Product(
                id = 6L,
                name = "Wireless Earbuds",
                price = 29900,
                imageUrl = "https://resource.logitech.com/content/dam/logitech/en/products/video-conferencing/zone-true-wireless-earbuds/zone-true-wireless-earbuds-gallery-1-graphite.png",
            ),
            Product(
                id = 7L,
                name = "Smartphone Case",
                price = 12000,
                imageUrl = "https://cdn-stamplib.casetify.com/cms/image/b32f44adda092acc8c81a66acbd54610.jpg",
            ),
            Product(
                id = 8L,
                name = "Bluetooth Speaker",
                price = 45000,
                imageUrl = "https://image10.coupangcdn.com/image/vendor_inventory/9c94/59c4a046068687ad284718f729a631279e6963a79865f671801867845318.jpg",
            ),
            Product(
                id = 9L,
                name = "Smartwatch",
                price = 159000,
                imageUrl = "https://image6.coupangcdn.com/image/retail/images/100810448383053-76bc9b64-7262-411a-b91c-c01e495dcc86.jpg",
            ),
            Product(
                id = 10L,
                name = "Portable Charger",
                price = 22000,
                imageUrl = "https://astroboyedition.com/web/product/big/202408/d02f69eec456b924363714d08b10bb6e.png",
            ),
            Product(
                id = 11L,
                name = "Wireless Earbuds",
                price = 29900,
                imageUrl = "https://resource.logitech.com/content/dam/logitech/en/products/video-conferencing/zone-true-wireless-earbuds/zone-true-wireless-earbuds-gallery-1-graphite.png",
            ),
            Product(
                id = 12L,
                name = "Smartphone Case",
                price = 12000,
                imageUrl = "https://cdn-stamplib.casetify.com/cms/image/b32f44adda092acc8c81a66acbd54610.jpg",
            ),
            Product(
                id = 13L,
                name = "Bluetooth Speaker",
                price = 45000,
                imageUrl = "https://image10.coupangcdn.com/image/vendor_inventory/9c94/59c4a046068687ad284718f729a631279e6963a79865f671801867845318.jpg",
            ),
            Product(
                id = 14L,
                name = "Smartwatch",
                price = 159000,
                imageUrl = "https://image6.coupangcdn.com/image/retail/images/100810448383053-76bc9b64-7262-411a-b91c-c01e495dcc86.jpg",
            ),
            Product(
                id = 15L,
                name = "Portable Charger",
                price = 22000,
                imageUrl = "https://astroboyedition.com/web/product/big/202408/d02f69eec456b924363714d08b10bb6e.png",
            ),
        )
}
