# 5장 제네릭

* 제네릭을 지원하기 전에는 컬렉션에서 객체를 꺼낼 때마다 형변환을 해야 했다. → 런타임 형변환 오류 발생 야기
* 반면, 제네릭을 사용하면 컬렉션이 담을 수 있는 타입을 컴파일러에 알려주게 된다. → 컴파일러가 알아서 형변환 코드 추가 + 컴파일 과정에서 엉뚱한 타입의 객체 차단.

## 아이템26 / 로 타입은 사용하지 말라

#### 제네릭 타입
* 클래스와 인터페이스 선언에 타입 매개변수가 쓰이면 이를 제네릭 클래스 혹은 제네릭 인터페이스라고 한다.
* 제네릭 클래스와 제네릭 인터페이스를 통틀어 제네릭 타입이라 한다.

#### 타입 선언의 필요성
* 컴파일러는 컬렉션에서 원소를 꺼내는 모든 곳에 보이지 않는 형변환을 추가하여 절대 실패하지 않음을 보장한다.
* → 매개변수화된 컬렉션 타입을 통해서 타입 안정성을 확보해야 한다. 그러지 않으면 컴파일러가 오류를 잡아내지 못한다.
* 로 타입을 쓰면 제네릭이 안겨주는 안전성과 표현력을 모두 잃게 된다.
* → List<Object> 같은 매개변수화 타입을 사용할 때와 달리 List 같은 로 타입을 사용하면 타입 안전성을 잃게 된다.

#### 로 타입을 쓰고 싶을 때
* 비한정적 와일드카드 타입을 대신 사용하는 게 좋다. → 물음표(?) 사용 / ex) Set<?>
* 와일드카드 타입은 안전하고, 로 타입은 안전하지 않다.
* 로 타입 컬렉션에는 아무 원소나 넣을 수 있으니 타입 불변식을 훼손하기 쉽다.
* 반면, Collection<?>에는 (null 외에는) 어떤 원소도 넣을 수 없다.

#### 로 타입을 쓰지 말라는 규칙의 예외
* class 리터럴에는 로 타입을 써야 한다. → ex) List<String.class, List<?>.class 허용x
* 런타임에는 제네릭 타입 정보가 지워지므로 instanceof 연산자는 비한정적 와일드카드 타입 이외의 매개변수화 타입에는 적용할 수 없다. → 비한정적 와일드카드는 아무런 역할 없이 코드만 지저분하게 만든다.

#### 핵심 정리
* 로 타입을 사용하면 런타임에 예외가 일어날 수 있으니 사용하면 안된다.
* Set<Object>는 어떤 타입의 객체도 저장할 수 있는 매개변수화 타입이고, Set<?>는 모종의 타입 객체만 저장할 수 있는 와일드카드 타입이다. 그리고 이들의 로 타입인 Set은 제네릭 타입 시스템에 속하지 않는다.
* Set<Object>와 Set<?>는 안전하지만, 로 타입인 Set은 안전하지 않다.

## 아이템27 / 비검사 경고를 제거하라

* 할 수 있는 한 모든 비검사 경고를 제거하면 그 코드는 타입 안전성이 보장된다.
* 경고를 제거할 수는 없지만 타입 안전하다고 확신할 수 있다면 @Suppress Warnings("unchecked") 애너테이션을 달아 새로운 경고가 파묻히지 않도록 하자.
* @SuppressWarning 애너테이션은 항상 가능한 한 좁은 범위에 적용하고 안전한 이유를 주석으로 남기자.

## 아이템28 / 배열보다는 리스트를 사용하라

#### 배열과 제네릭 타입의 차이
* 배열은 공변이다. Sub가 Super의 하위 타입이라면 배열 Sub[]는 배열 Super[]의 하위 타입이 된다.
* 제네릭은 불공변이다. 서로 다른 타입 Type1과 Type2가 있을 때, List<Type1>은 List<Type2>의 하위 타입도 아니고 상위 타입도 아니다.
* 배열은 런타임에도 자신이 담기로 한 원소의 타입을 인지하고 확인한다.
* 제네릭은 타입 정보가 런타임에는 소거된다. 원소 타입을 컴파일타임에만 검사하며 런타임에는 알 수 조차 없다는 뜻이다.
* -> 이러한 이유로 배열과 제네릭은 잘 어우러지지 못하며, 타입 안전하지 않기 때문에 제네릭 배열을 만들지 못하게 막았다.

#### 배열을 리스트로
* 배열과 제네릭에는 매우 다른 타입 규칙이 적용된다.
* 둘을 섞어 쓰다가 컴파일 오류나 경고를 만나면, 배열을 리스트로 대체하는 방법을 적용해보자.

## 아이템29 / 이왕이면 제네릭 타입으로 만들라

#### 일반 클래스를 제네릭 클래스로 만들어보기
1. 클래스 선언에 타입 매개변수를 추가한다. 이때 타입 이름으로는 보통 E를 사용한다.
2. 코드에 쓰인 Object(예시)를 적절한 타입 매개변수로 바꾸고 컴파일 한다.
3. 오류가 발생하면 해경한다.

#### 배열을 사용한 코드를 제네릭으로 만드는 방법
1. Object 배열을 생성한 다음 제네릭 배열로 형변환 한다. → 타입 안전하지 않으므로 경고를 보낸다. → 비검사 형변환이 안전함을 직접 증명했다면 @Suppress Warnings 애너테이션으로 해당 경고를 숨긴다.
2. 필드의 타입을 E[]에서 Object[]로 바꾼다. → 배열이 반환한 원소를 E로 형변환 하면 경고를 보낸다. → 비검사 형변환이 안전함을 직접 증명했다면 @Suppress Warnings 애너테이션으로 해당 경고를 숨긴다.
* 첫 번째 방법은 가독성이 더 좋다. 그러나 배열의 런타임 타입이 컴파일타임 타입과 달라 힙 오염을 일으켜서 두 번째 방식을 사용하기도 한다.

#### 제네릭 타입의 배열 사용
* 자바가 리스트를 기본 타입으로 제공하지 않으므로 ArrayList 같은 제네릭 타입도 결국은 기본 타입인 배열을 사용해 구현해야 한다.
* 제네릭 타입은 타입 매개변수에 아무런 제약을 두지 않는다. (기본 타입 int, double 등은 제외)

#### 한정적 타입 매개변수
* 타입 매개변수에 제약을 둔다. ex) <E extends Delayed>

#### 핵심 정리
클라이언트에서 직접 형변환해야 하는 타입보다 제네릭 타입이 더 안전하고 쓰기 편하므로 제네릭 타입으로 만들자.

## 아이템30 / 이왕이면 제네릭 메서드로 만들라

#### 제네릭 메서드 만들기
* 메서드 선언에서 입력과 반환의 원소 타입을 타입 매개변수로 명시하고, 메서드 안에서도 이 타입 매개변수만 사용하게 수정한다.
* → (타입 매개변수들을 선언하는) 타입 매개변수 목록은 메서드의 제한자와 반환 타입 사이에 온다.
* ex) public static <E> Set<E> union(Set<E> s1, Set<E> s2) { ... }
* 한정적 와일드카드 타입을 사용하면 유연하게 개선이 가능하다.
* 불변 객체를 여러 타입으로 활용할 수 있게 만들어야 하는 경우, 요청한 타입 매개 변수에 맞게 매번 그 객체의 타입을 바꿔주는 제네릭 싱글턴 팩터리를 만든다.
* 재귀적 타입 한정: 자기 자신이 들어간 표현식을 사용하여 타입 매개변수의 허용 범위를 한정할 수 있으며, 주로 타입의 자연적 순서를 정하는 Comparable 인터페이스와 함께 쓰인다.

#### 핵심 정리
제네릭 타입과 마찬가지로 클라이언트에서 입력 매개변수와 반환값을 명시적으로 형변화해야 하는 메서드보다 제네릭 메서드가 더 안전하며 사용하기도 쉽다.
