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



### Lazy 컴포넌트의 작동 원리

### 테스트를 위한 contentDescription 작성은 선택이 아닌 필수인가?

### LocalContext.current를 사용할 때 고려사항이 무엇인가?
