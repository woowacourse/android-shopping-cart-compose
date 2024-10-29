package nextstep.shoppingcart.domain

object ProductRepository {

    val dummy: List<Product> = listOf(
        Product(
            0,
            "미우미우 - 오드리햅번ver",
            27000,
            "https://github.com/user-attachments/assets/5470cb47-32c7-485f-9941-85eb12fc8361",
        ),
        Product(
            1,
            "아기 복숭아 - 173cm",
            50000,
            "https://github.com/user-attachments/assets/3e627034-236d-4516-9689-bd6d6f33971b",
        ),
        Product(
            2,
            "궁극의 아이돌 - 콘서트",
            72000,
            "https://github.com/user-attachments/assets/bec777ce-ac0c-48b7-8d40-997babc8a02c",
        ),
        Product(
            3,
            "탤마뿌럼못날뤼가 - smart",
            25400,
            "https://github.com/user-attachments/assets/f9576cfd-6b8a-47a3-ac3d-6ae267373bfa",
        ),
        Product(
            4,
            "스빠이쉬쓰빠이쉬쓰빠이쉬쓰빠이쉬 - spicy",
            123456,
            "https://github.com/user-attachments/assets/c9f30f84-3d73-420a-9f0f-52f4df47a36d",
        ),
        Product(
            5,
            "이부자리 - 팬싸템ver",
            73920,
            "https://github.com/user-attachments/assets/a1088a04-7e00-4c86-b5ac-bfff1a825910",
        ),
        Product(
            6,
            "아기사막여우 - 키스오브라이프뜻이인공호흡이래요",
            353534,
            "https://github.com/user-attachments/assets/358cf2a6-7b4a-40a2-b6e5-ffa6303eab27",
        ),
        Product(
            7,
            "위플래쉬 - 개띵곡",
            5383,
            "https://github.com/user-attachments/assets/bdbe4e39-3852-4fd8-8e7c-5173bfa716fa",
        ),
        Product(
            8,
            "삐끼삐끼삐끼삐끼 - 프미나",
            23452363,
            "https://github.com/user-attachments/assets/7d69b1b6-bf6c-4bc9-af82-88613ac32ac6",
        ),
        Product(
            9,
            "천년의 아이돌 - 미스터츄",
            3582398,
            "https://github.com/user-attachments/assets/50ee770a-6155-4021-a849-27a5a4078065",
        ),
    )

    fun productById(id: Long): Product =
        dummy.find { it.id == id } ?: throw IllegalArgumentException("$id 에 해당하는 값이 없습니다.")
}