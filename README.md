# android-shopping-cart

# 기능 요구 사항

- [x] 상품 목록 화면을 구현한다.
- [x] 상품 목록 화면을 구현할 때 Lazy 컴포넌트를 활용한다.
- [x] 상품 상세 화면을 구현한다.
- [x] 상품 목록에서 상품을 누르면 상품 상세 화면으로 이동한다.
- [x] 뒤로 가기 버튼이나 아이콘을 누르면 직전 화면으로 돌아간다.
- [x] 장바구니 화면의 빈 껍데기를 연결한다.
- [x] 상품 목록에서 장바구니 아이콘을 누르면 장바구니 화면으로 이동한다.
- [x] 상품 상세에서 장바구니 담기 버튼을 누르면 장바구니 화면으로 이동한다.
- [x] 뒤로 가기 버튼이나 아이콘을 누르면 직전 화면으로 돌아간다.
- [x] 장바구니에 실제로 상품이 담기는 기능은 이 단계에서 고려하지 않는다.
- [x] 상품을 장바구니에 담는 기능을 구현한다.
- [x] 장바구니 화면을 구현한다.
- [x] 담긴 상품의 수량을 조절할 수 있어야 한다.
- [x] 수량을 1보다 작게 하면 장바구니에서 상품이 제거된다
- [x] 담긴 상품 가격의 총합이 주문하기 버튼에 표시된다.
- [x] 장바구니 화면에 대한 테스트 코드를 작성한다.
- [] 상품 목록에서 장바구니에 담을/담긴 상품의 수량을 조절할 수 있다.
- [] 아이콘을 누르면 장바구니에 상품이 추가됨과 동시에 수량 조절 버튼이 노출된다.
- [] 상품 목록의 상품 수가 변화하면 장바구니에도 반영되어야 한다. (B마트 UX 참고)
- [] 장바구니의 상품 수가 변화하면 상품 목록에도 반영되어야 한다. (B마트 UX 참고)
- [] 반복되는 뷰(상품 수량 조절)를 재사용할 수 있는 방법을 고민해 본다.
- [] 3단계에서 작성된 장바구니 화면 테스트가 실패하면 안 된다.

# 낙서장

## Coil vs Glide

Coil은 kotlin으로 구현된 이미지 처리 라이브러리로, Coroutine을 통해 이미지를 로드하여
BackgroundThread에서 동작하는 Glide와 달리, Native Code 호출을 줄여 메모리 효율이 높고 성능이 우수함.

Kotlin 으로 구현된 이미지 처리 라이브러리
Coroutine 사용하여 이미지를 로드함
Coroutine 을 사용하므로, BackgroundThread 를 사용하는 Glide 와 다르게 Native Code 호출 메모리가 적음 즉, 성능이 좋음

### 예시 코드

```kotlin
// Coil
AsyncImage(
    modifier = Modifier
        .weight(1f)
        .height(60.dp),
    model = image,
    contentDescription = "image",
    contentScale = ContentScale.Crop
)

// Glide
GlideImage(
    modifier = Modifier
        .weight(1f)
        .height(60.dp),
    model = image,
    contentDescription = "image",
    contentScale = ContentScale.Crop
)
```

코드에서 단순한 이미지 로드 시에는 Coil과 Glide 모두 사용법이 비슷하다.

### 특성 비교

Coil과 Glide는 단순 이미지 로드에서는 유사한 구문을 사용하지만,
확장 기능을 추가하거나 에러 처리를 구현할 때 차이가 두드러진다.

### Glide의 경우, 추가적인 속성 구현 시 코드가 복잡해지는 경향이 있다.

```kotlin

GlideImage(
    modifier = Modifier.size(24.dp),
    model = author?.profilePath ?: "",
    contentDescription = "profileImage",
    requestBuilderTransform = object : RequestBuilderTransform<Drawable> {
        override fun invoke(p1: RequestBuilder<Drawable>): RequestBuilder<Drawable> {
            return p1.thumbnail()
        }
    }
)
```

Coil은 에러 처리 코드가 상대적으로 더 간결하며 명확하다.

```kotlin

AsyncImage(
    modifier = Modifier
        .fillMaxWidth()
        .height(120.dp),
    model = brand.imagePath?.get(0) ?: "",
    contentDescription = "brandLogoImage",
    contentScale = ContentScale.Inside,
    error = painterResource(id = R.drawable.ic_image_error)
)

GlideImage(
    modifier = Modifier.size(24.dp),
    model = author?.profilePath ?: "",
    contentDescription = "profileImage",
    requestBuilderTransform = {
        it.error(ContextCompat.getDrawable(context, R.drawable.ic_image_error))
    }
)

```

### 정리하면..

- Coil은 Kotlin의 장점을 살려 코드가 깔끔하며, 특히 에러 처리에서 더 간편하다.
- Coroutine을 사용해 Glide보다 Native Code 호출이 적어 메모리 효율성이 높다.
- Glide는 다양한 이미지 처리를 지원하지만 코드가 복잡해질 수 있다.

# Lazy 컴포넌트의 작동 원리

Lazy 컴포넌트는 Jetpack Compose에서 대량의 리스트나 그리드 데이터를 효율적으로 처리하기 위해 사용하는 컴포저블이다.
리스트나 그리드를 스크롤할 때 필요한 항목만 렌더링하여, 성능을 높이는 방식으로 작동한다.

LazyColumn과 LazyRow 같은 Lazy 컴포넌트들은 실제로 사용자가 보고 있는 항목만 메모리에 유지하고,
스크롤될 때 필요한 새로운 항목을 동적으로 생성하여 메모리를 효율적으로 사용한다.

### 작동 방식의 주요 포인트

- 항목을 미리 로드하지 않는다. 즉, 스크롤되지 않는 항목은 화면에 렌더링되지 않으므로 불필요한 메모리 사용을 줄인다.
- 재사용한다. 기존에 스크롤을 통해 사라진 항목은 메모리에서 해제되므로 필요할 때 다시 생성되며, 이렇게 메모리를 효율적으로 관리한다.
- LazyColumn 내부에서는 LazyListScope의 items 함수를 사용하여 각 항목의 구성을 선언적으로 정의할 수 있다. 이로 인해 코드의 가독성이 높아지고 구조화가
  용이해진다.

이러한 구조 덕분에 Lazy 컴포넌트는 RecyclerView와 비슷한 방식으로 효율적으로 작동하며, 스크롤 성능을 개선하고 메모리를 절약하는 데 효과적이다.

### state는 뭐야?

state는 리스트의 스크롤 위치와 같은 상태를 관리하는 데 사용된다.
이를 통해 현재 스크롤 위치를 저장하거나, 사용자가 나중에 리스트에 다시 돌아왔을 때 이전 스크롤 위치를 복원할 수 있다.

```kotlin
val listState = rememberLazyListState()

LazyColumn(state = listState) {
    items(100) { index ->
        Text("Item #$index")
    }
}
```

listState.firstVisibleItemIndex: 현재 화면에 보이는 첫 번째 항목의 인덱스
listState.firstVisibleItemScrollOffset: 첫 번째 항목의 스크롤 오프셋

### key는 뭐야?

key는 리스트의 각 항목을 고유하게 식별하는 데 사용된다.
데이터가 변경되거나 리스트가 스크롤될 때 항목이 재구성되지 않도록 보장한다.

```kotlin
val items = listOf("Apple", "Banana", "Cherry")

LazyColumn {
    items(items, key = { it }) { item ->
        Text(text = item)
    }
}
```

key가 없으면 항목이 순서에 따라 식별되어 데이터가 변경될 때 항목이 잘못 재사용될 가능성이 있다.
key는 리스트 아이템의 id와 같은 고유 값을 가지므로, 스크롤 시 항목의 순서가 유지된다.

# 테스트를 위한 contentDescription 작성은 선택이 아닌 필수인가?

`contentDescription`은 시각적 요소에 대한 설명을 제공하여 시각 장애인이 사용하는 스크린 리더가 해당 요소의 의미를 이해할 수 있도록 돕는 역할을 한다. 

UI 테스트를 수행할 때 `onNodeWithText`와 `SemanticsMatcher`로 찾을 수 없는 Composable이 있을 수 있다. (Icon의 경우 찾지 못하는 것으로 보인다..)
이때 `contentDescription`은 이러한 요소를 식별하는 데 중요한 역할을 한다.

결론적으로, Compose에서 UI 요소를 만들 때 `contentDescription`을 작성하는 것은 필수적이라고 할 수 있다.


# LocalContext.current를 사용할 때 고려사항이 무엇인가?

`LocalContext.current`는 `@Composable` 함수에서 현재 `Context` 객체를 가져오는 데 사용되며,
일반적으로 Android의 `Activity`, `ApplicationContext`, 또는 `Resources`에 접근할 때 활용된다.

`LocalContext.current`를 사용할 때 어떤 것을 고려해야할까??

### 리컴포지션에 주의

`LocalContext.current`는 `Context`의 변경에 영향을 받지 않으므로 일반적으로 리컴포지션에 의해 값이 변경되지 않는다.
그러나 `Context`에 의존하는 경우(테마가 변경되는 등), `LocalContext`에 의해 리컴포지션이 발생할 가능성이 있으므로, 값 참조가 반복적으로 수행되지
않도록 필요한 경우 미리 저장해두는 것이 좋다.
