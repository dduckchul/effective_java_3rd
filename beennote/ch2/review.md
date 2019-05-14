# 2장 객체 생성과 파괴

## 아이템1 / 생성자 대신 정적 팩터리 메서드를 고려하라

클래스는 생성자와 별도로 정적 팩터리 메서드를 제공할 수 있다.

#### 장점
1. 이름을 가질 수 있다.
- 하나의 시그니처로는 생성자를 하나만 만들 수 있다. 반면 정적 팩터리 메서드에는 그런 제약이 없어 각각의 차이를 잘 드러내는 이름을 지어줄 수 있다.
2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
- Boolean.valueOf(boolean) 메서는 객체를 아예 생성하지 않는데 이처럼 불필요한 객체 생성을 피할 수 있다. 특징은 인스턴스를 철저히 통제할 수 있는데 이를 인스턴트 통제 클래스라 한다.
3. 반환 타입의 하위 타입 객체를 반활할 수 있는 능력이 있다.
- 반환할 객체의 클래스를 자유롭게 선택할 수 있게 하는 엄청난 유연성을 선물한다. API를 만들 때 이 유연성을 응용하면 구현 클래스를 공개하지 않고도 그 객체를 반환할 수 있어 API를 작게 유지할 수 있다.
*자바8 이전에는 인터페이스에 정적 메서드를 선언할 수 없어 (인스턴트화 불가인) 동반 클래스를 만들어 그 안에 정의하는 것이 관례였다. Ex) java.util.Collections에서 정적 팩터리 메서드를 통해 구현체를 얻음.
4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
- 반환 타입의 하위 타입이기만 하면 어떤 클래스의 객체를 반환하든 상관없다. Ex) EnumSet 클래스의 원소가 64개 이하면 RegularEnumSet의 인스턴스를, 65개 이상이면 JumboEnumSet의 인스턴스를 반환한다.
5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
- 이런 유연함은 서비스 제공자 프레임워크를 만드는 근간이 된다. 서비스 제공자 프레임워크에서의 제공자는 서비스의 구현체다. 그리고 이 구현체들을 클라이언트에 제공하는 역할을 프레임워크가 통제하여, 클라이언트를 구현체로부터 분리해준다.

#### 단점
1. 상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다.
- 컬렉션 프레임워크 등의 유틸리티 구현 클래스들은 상속할 수 없다는 이야기이다.
2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.
- 생성자처럼 API 설명에 명확히 드러나지 않으니 API 문서를 잘 써놓고 메서드 이름도 널리 알려진 규약을 따라 짓는 식으로 문제를 완화해줘야 한다. (p13)

#### 핵심 정리
정적 팩터리 메서드와 public 생성자는 각자의 쓰임새가 있으니 상대적인 장단점을 이해하고 사용하는 것이 좋다. 그렇다고 하더라도 정적 팩터리를 사용하는 게 유리한 경우가 더 많으므로 무작정 public 생성자를 제공하던 습관이 있다면 고치자.


## 아이템2 / 생성자에 매개변수가 많다면 빌더를 고려하라
* 정적 팩터리와 생성자에는 모두 선택적 매개변수가 많을 때 적절히 대응하기 어렵다는 제약이 있다.
* 주로 점층적 생성자 패턴을 사용하는데, 매개변수 개수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵다.
* 두번째 대안인 자바빈즈 패턴을 살펴보면 매개변수가 없는 생성자로 객체를 만든 후, 세터 메서드들을 호출해 원하는 매개변수의 값을 설정한다. 인스턴스를 만들기 쉽고 더 읽기 쉬운 코드가 되었지만, 객체 하나를 만들려면 메서드를 여러 개 호출해야 하고 객체가 완전히 생성되기 전까지는 일관성이 무너진 상태에 놓이게 된다. Freeze 메서드를 활용하는 방법도 있는데 실전에서는 거의 쓰이지 않는다.
* 세번째 대안은 점층적 생성자 패턴의 안전성과 자바빈즈 패턴의 가독성을 겸비한 빌더 패턴이다. 클라이언트는 필요한 객체를 직접 만드는 대신, 필수 매개변수만으로 생성자를 호출해 빌더 객체를 얻는다. 그런 다음 빌더 객체가 제공하는 일종의 세터 메서드들로 원하는 선택 매개변수들을 설정한다. 마지막으로 매개변수가 없는 build 메서드를 호출해 우리에게 필요한 객체를 얻는다.
* 빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기에 좋다. 각 계층의 클래스에 관련 빌더를 멤버로 정의하자. 추상 클래스는 추상 빌더를, 구체 클래스는 구체 빌더를 갖게 한다. 빌더 패터는 상당히 유연하다. 빌더 하나로 여러 객체를 순회하면서 만들 수 있고, 빌더에 넘기는 매개변수에 따라 다른 객체를 만들 수도 있다.

#### 핵심정리
생성자나 정적 팩터리가 처리해야 할 매개변수가 많다면 빌더 패턴을 선택하는 게 더 낫다.


## 아이템3 / private 생성자나 열거 타입으로 싱글턴임을 보증하라
싱글턴을 만드는 방식
1. public static final 필드 방식의 싱글턴
- 장점: 해당 클래스가 싱글턴임이 API에 명백히 드러난다, 간결하다.
2. 정적 팩터리 방식의 싱글턴
- 장점: API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다. 원한다면 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다. 정적 팩터리의 메서드 참조를 공급자로 사용할 수 있다.
3. 열거 타입 방식의 싱글턴 – 바람직한 방법
- 장점: 간결하고 추가 노력 없이 직렬화할 수 있고 복잡한 직렬화 상황이나 리플렉션 공격에서도 제2의 인스턴스가 생기는 일을 완벽히 막아준다.


## 아이템4 / 인스턴스화를 막으려거든 private 생성자를 사용하라
정적 멤버만 담은 유틸리티 클래스는 인스턴스로 만들어 쓰려고 설계한 게 아니다. 하지만 생성자를 명시하지 않으면 컴파일러가 자동으로 기본 생성자를 만들어준다. 추상 클래스로 만드는 것으로는 인스턴스화를 막을 수 없다. 이때 private 생성자를 추가하면 클래스의 인스턴스화를 막을 수 있다.


## 아이템5 / 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라
사용하는 자원에 따라 동작이 달라지는 클래스에는 정적 유틸리티 클래스나 싱글턴 방식이 적합하지 않다. 인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨주는 방식을 사용해야하며 이는 의존 객체 주입의 한 형태이다.

#### 핵심정리
클래스가 내부적으로 하나 이상의 자원에 의존하고 그 자원이 클래스 동작에 영향을 준다면 싱글턴과 정적 유틸리티 클래스는 사용하지 않는 것이 좋다. 대신 필요한 자원을 생성자에 넘겨주자. 의존 객체 주입이라 하는 이 기법은 클래스의 유연성, 재사용성, 테스트 용이성을 개선한다.


## 아이템6 / 불필요한 객체 생성을 피하라
* 똑같은 기능의 객체를 매번 생성하기보다는 객체 하나를 재사용하는 편이 나을 때가 많다. 생성자는 호출할 때마다 새로운 객체를 만들지만 팩터리 메서드는 전혀 그렇지 않다. 생성 비용이 아주 비싼 객체의 경우 정적 초기화 등의 방법으로 직접 생성해 캐싱하여 재사용하길 권한다.
* 객체 생성은 비싸니 피해야한다는 것이 아니다. 단순히 객체 생성을 피하고자 자신만의 객체 풀을 만들지도 말자. 요즘 JVM의 가비지 컬렉터는 상당히 잘 최적화되어있다.


## 아이템7 / 다 쓴 객체 참조를 해제하라
자바의 가비지 컬렉터 덕에 메모리 관리에 더 이상 신경 쓰지 않아도 된다고 오해할 수 있는데 절대 사실이 아니다. 자기 메모리를 직접 관리하는 클래스라면 프로그래머는 항시 메모리 누수에 주의해야 한다. 캐시 역시 메모리 누수를 일으키는 주범이다. 리스너 혹은 콜백을 등록만 하고 명확히 해지하지 않으면 메모리 누수가 생긴다.

#### 핵심정리
메모리 누수는 겉으로 잘 드러나지 않아 시스템에 수년간 잠복하는 사례도 있다. 이런 종류의 문제는 예방법을 익혀두는 것이 매우 중요하다.


## 아이템8 / finalizer와 cleaner 사용을 피하라
* 자바는 두 가지 객체 소멸자를 제공한다.
* finalizer는 예측할 수 없고 상황에 따라 위험할 수 있어 일반적으로는 불필요하다.
* cleaner는 finalizer보다는 덜 위험하지만 여전히 예측할 수 없고, 느리고, 일반적으로 불필요하다.
* 두 소멸자 모두 즉시 수행된다는 보장이 없어서 제때 실행되어야 하는 작업은 절대 할 수 없다. 또한 프로그램 생애주기와 상관없는, 상태를 영구적으로 수정하는 작업에서는 절대 finalizer나 cleaner에 의존해서는 안된다. 심각한 성능 문제도 있을뿐 아니라 보안 문제도 존재한다
* 적절한 쓰임새엔 두 가지가 있다. 하나는 자원의 소유자가 close 메서드를 호출하지 않는 것에 대비한 안전망 역할이다. 두 번째는 네이티브 피어(일반 자바 객체가 네이티브 메서드를 통해 기능을 위임한 네이티브 객체)와 연결된 객체에서다.

#### 핵심정리
cleaner는 안전망 역할이나 중요하지 않은 네이티브 자원 회수용으로만 사용하자. 물론 이런 경우라도 불확실성과 성능 저하에 주의해야 한다.


## 아이템9 / try-finally보다는 try-with-resources를 사용하라
* try-finally를 여러 자원에 사용할 경우 코드가 복잡해지고 실수가 발생할 수 있음.
* try-with-resources는 짧고 읽기 수월할 뿐 아니라 문제를 진단하기도 훨씬 좋다.

#### 핵심정리
예외없이 try-with-resources를 사용하자. 코드는 더 짧고 분명해지고 만들어지는 예외 정보도 훨씬 유용하다.

