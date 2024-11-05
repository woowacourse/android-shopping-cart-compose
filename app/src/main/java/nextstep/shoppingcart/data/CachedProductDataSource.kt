package nextstep.shoppingcart.data

import nextstep.shoppingcart.domain.model.Product

class CachedProductDataSource : ProductDataSource {
    private val products: MutableList<Product> =
        mutableListOf<Product>(
            Product(
                id = 1,
                name = "PET보틀-정사각형 용기 (1L)",
                price = 10_000,
                imageUrl = "https://s3-alpha-sig.figma.com/img/05ef/e578/d81445480aff1872344a6b1b35323488?Expires=1731888000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=KNMX7pZxzQz578ovhuWIBF2ilsQACu~sph~2fwFZyhO3pX9vhCd-kXHJmW5lpogb2IhP6bvH1N8qCKX~jHluc-tDSLD4EbUsGI9yJqanYsdOSRY6KKPyrh1SekXEH8u4-A77xal1TEj99wTnMMGKGXlI4UJhQJhg6hhBRL3ONv5Y1N5~2yMw4M54bGJi4HQYh3eG5rcPrK96KWonhd7BtZDwo4MMwK1TQ0FjuIBMdoabOACAwRwzE-EJ8znP71oTiOsZES6wASv21meilUhN~A8C72-qqNhyybuDTNqZQfQIHWT7kh-Lm0McKRQUg7~OSs3nWVJR7O-USQCwHhhclQ__",
            ),
            Product(
                id = 2,
                name = "PET보틀-밀크티 용기",
                price = 12_000,
                imageUrl = "https://s3-alpha-sig.figma.com/img/f081/71c9/4a1459fb4310f704c34be19a15f662a4?Expires=1731888000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=ZKYEUR3xhp1aA4aULlmTfL1WE3xPvkK14FKZ6ih6JJyFdt54d3srSsB3OG2qUffmjzxsN-SgL4vtdmJgAyVeUh4JZlgBrrDOeBnsvqx3E3myqzO3iBZMsqG54zici8-pht8vl22MhbV7rzqNA5WSbSGbVztUhS1J8Rm9u9wDH5RY9vfbf-92vvfkRzakG84NghCmq~8fNusILMtEMlUBM~aY02owPo7MIsDLmwHBhGSThU5mnNCawKmmZc1ESwvjzeYhgY6HY~U9v24M1nlKL6KFA4wu8KuIfDv9G1-pve6P3i4DZKbgir19Q46pHGp0VErbA2yNhpWwu4MvYKJ~5w__",
            ),
            Product(
                id = 3,
                name = "PET보틀-정사각형 용기 (500ml)",
                price = 10_000,
                imageUrl = "https://s3-alpha-sig.figma.com/img/ff9f/d83e/b40e670bfc38dbcddbcec8b3ca363d50?Expires=1731888000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=DzhgzJzS4g6I3SO2RtuhKgWdng1dPjM2aPUASlWNq8VvjOZMd9tfYaruZLALMCKtx~cmq~Q6~hWr1vAnbHBZsRW~8EIwneiuNLE-fxFuTjFBV24G2u8LpY5n91oMgGYzNqxtuTVVqE28~GUhDHsxQt5BL5tUukHeER0-mUg4wUP-4uWA-zN9m864LZwYpTLxzcNCOAuvgFAR2HHsGMJCDyC~Fzd7lSLVuFQVwxF7kolB9ErqqVUA2SO0wjfCoHoWJUwuTlSr9jSGW4-h4lJafTpGvYJCS4foasIYy3ODeeOgBOCgnXkHdrJivZlkR0gNPCOOx2x~KDWV547AsrO5YA__",
            ),
            Product(
                id = 4,
                name = "PET보틀-납작 용기 (500ml)",
                price = 12_000,
                imageUrl = "https://s3-alpha-sig.figma.com/img/b8b6/a740/d8661b91e8210779ce9db930d260230d?Expires=1731888000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=lYXFy7NZnn2Pe7vn29qkin2d9HPRXteVaHuLXIH2pxjspZP1GMyr8d9It1WcYND8apF~LTd13z-ckQ9WrZO-ExycyxF6H1oVSOQmDQfxAgzpGDfmJlNiEh7BoFllbnQVPsHwfFYaGxSFr93zYkzNKfwQGUTltozO8fElpK0dQePtyIzuZpmyHegnizh~hqdlx9YlyJdfeuV-AlAtyKZjSFDcphz3RJ5YujI0xzf5XEGoYGBN5piSwr-41UWXY664LcmUVRZF9Ge8hZdyV1vu2Coty54x7ns5FbTu3GUhcZ3bSrTYaQcDzlvggtYXel~SPoV20d8xPwXMFRCbSuUxhw__",
            ),
            Product(
                id = 5,
                name= "PET보틀-원형 용기 (500ml)",
                price = 10_000,
                imageUrl = "https://s3-alpha-sig.figma.com/img/8f83/c0e8/c17365cdfe5a00fa17c0283d520f4a99?Expires=1731888000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Pt91VGcwcypqZ0ZiBhcXNVlgJ7x7yrNsh0ucdfSZ1Ct12CR0eTe7G4a4doBtthWGWIL-Tmj4VYvqbMNMT3McEbGmgR~jvfIYhAO1rQaSO6Ry69kSV1K3qpaqNyW7WkfoKi2-qj4w9665rOYY~ss-H3Le7YAkpnbEKbZUBfGFodaHSEwRplcT5V~ECB65DGoevOqMg4ArLdtJSDNpOdbb166Q8LSzcmZ0sOksimWDBLKegKjWtwi30Fe6WBJzmLD5i7TBXpxKgOOlL92M0zT~kgLIAIEM2C4g8qvJWqE74d4NX~y2c-AsU7pcLQqrHOU7oElPyPXhlnZkxvPd86cyIw__",
            ),
            Product(
                id = 6,
                name = "PET보틀-납작 용기 (200mL)",
                price = 12_000,
                imageUrl = "https://s3-alpha-sig.figma.com/img/b9f2/403d/b915b1b22edac0877abb7b97129296b6?Expires=1731888000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=YUOtsuzD0QhxPGoaGPNVb3IfJu015CI0LkvUaywB3qcTHOmBclM6YsRCba5lGrMq3PY5MbG7U4VPbbhq8z5RBcGyxIb9M31qTYIEYTstE1-C2zq0IR3YXwMqaV5t9A7hjClBnoZl~jT2DTH4k5YxrIRy2D~qqR57C-4TF-Doo3Xhs~jNvRCyYYZBvFqg0PerspsQHnruNK9sJCVvWB4dC9DN290uU7jmbOrO6ku0SCtFp7BSCAP2l4FwW3LcIGAJpPRZSAqZu-z-n8QnEFJnWWDBqGynQqQQq9jQqxEW15gOna8a3X0nTM4RrTR6gZX2aKWMhOtRcciMoLwaiar5bw__",
            ),
            Product(
                id = 7,
                name = "name1",
                price = 1000,
                imageUrl = "https://thumbnail7.coupangcdn.com/thumbnails/remote/292x292ex/image/retail/images/1263603036762773-f6291401-9c64-4944-8189-86e5aead6049.png",
            ),
            Product(
                id = 8,
                name = "name2",
                price = 2000,
                imageUrl = "https://thumbnail7.coupangcdn.com/thumbnails/remote/292x292ex/image/vendor_inventory/e294/d78edd81a8f38ae32984e9ad3393a840bd9ccdc91161838a8036f4d90434.jpg",
            ),
            Product(
                id = 9,
                name = "name3",
                price = 3000,
                imageUrl = "https://thumbnail7.coupangcdn.com/thumbnails/remote/292x292ex/image/1025_amir_coupang_oct_80k/e308/9c53df34079cb2e6a8123f93355a796ae18b7979bc61bd360da0793314af.jpg",
            ),
        )

    override fun fetchProducts(): List<Product> = products.toList()

    override fun findProduct(id: Long): Product = products.firstOrNull { it.id == id } ?: Product.NULL_PRODUCT
}
