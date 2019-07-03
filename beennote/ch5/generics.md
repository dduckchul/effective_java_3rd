# 자바의 정석_지네릭스

#### 지네릭스란?
* 지네릭스는 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에 컴파일 시의 타입 체크(compile-time type check)를 해주는 기능이다.
* 객체의 타입을 컴파일 시에 체크하기 때문에 객체의 타입 안정성을 높이고 형변환의 번거로움이 줄어든다.
* 지네릭스의 장점: 1) 타입 안정성을 제공한다. 2) 타입체크와 형변환을 생략할 수 있으므로 코드가 간결해진다.

#### 지네릭 클래스의 선언
```java
class Box<T>
  T item;
  
  void setItem(T item)  { this.item = item; }
  T getItem() { return item; }
```
* T(Type), E(Element), K(Key), V(Value): 타입 변수라고 하며 기호의 종류만 다를 뿐 임의의 참조형 타입을 의미한다는 것은 모두 같다.
* 지네릭스가 도입되기 이전의 코드와 호환성을 유지하기 위해서 지네릭스를 사용하지 않은 코드(로 타입)를 허용하는 것일 뿐, 앞으로 지네릭 클래스를 사용할 때는 반드시 타입을 지정해서 지네릭스와 관련된 경고가 나오지 않도록 하자.
* 지네릭 타입 호출: 타입 매개변수에 타입을 지정하는 것.
* 매개변수화된 타입: 지정된 타입을 지칭

#### 지네릭스의 제한
* static멤버에 타입 변수 T를 사용할 수 없다. T는 인스턴스변수로 간주되며 static멤버는 인스턴스변수를 참조할 수 없다.
* 지네릭 타입의 배열을 생성하는 것도 허용되지 않는다.
* instanceof 연산자와 new연산자는 컴파일 시점에 타입 T가 뭔지 알아야 하지만 컴파일 하는 시점에서는 T가 어떤 타입이 될지 전혀 알 수 없으므로 T를 피연산자로 사용할 수 없다.
* 지네릭 배열을 생성할 필요가 있을 때는 Object배열을 생성해서 복사한 다음, T로 형변환하는 방법 등을 사용한다.

#### 지네릭 클래스의 객체 생성과 사용
```java
Box<Apple> appleBox = new Box<Apple>(); // OK
Box<Apple> appleBox = new Box<Apple>(); // 에러
Box<Fruit> appleBox = new Box<Apple>(); // 에러. 대입된 타입이 다르다.
Box<Apple> appleBox = new FruitBox<Apple>(); // OK. 다형성
Box<Apple> appleBox = new Box<>(); // OK. JDK1.7부터 생략 가능

Box<Apple> appleBox = new Box<Apple>();
appleBox.add(new Apple()); // OK.
appleBox.add(new Grape()); // 에러. Box<Apple>에는 Apple 객체만 추가 가능.

Box<Fruit> fruitBox = new Box<Fruit>();
fruitBox.add(new Fruit()); // OK.
fruitBox.add(new Apple()); // Ok. void add(Fruit item)
```

#### 제한된 지네릭 클래스
```java
class FruitBox<T extends Fruit> { // Fruit의 자손만 타입으로 지정 가능
  ArrayList<T> list = new ArrayList<T>();
```
* 다형성에서 조상타입의 참조변수로 자손타입의 객체를 가리킬 수 있는 것처럼, 매개변수화된 타입의 자손 타입도 가능하다.
* 인터페이스를 구현해야 한다는 제약이 필요하다면, 이때도 'extends'를 사용한다.
```java
class FruitBox<T extends Fruit & Eatable> { // '&'기호로 연결 가능
  ArrayList<T> list = new ArrayList<T>();
```

#### 와일드 카드
* 지네릭 타입이 다른 것만으로는 오버로딩이 성립하지 않는다. (지네릭 타입은 컴파일러가 컴파일할 때만 사용하고 제거해버린다.)
* 와일드 카드(?)
```
<? extends T> 와일드 카드의 상한 제한. T와 그 자손들만 가능
<? super T>   와일드 카드의 하한 제한. T와 그 조상들만 가능
<?>           제한 없음. 모든 타입이 가능. <? extends Object>와 동일
```

#### 지네릭 메서드
* 메서드의 선언부에 지네릭 타입이 선언된 메서드를 지네릭 메서드라 한다.
* 지네릭 타입의 선언 위치는 반환 타입 바로 앞이다.
```java
static <T> void sort(List<T> list, Comparator<? super T> c)
```
* 지네릭 클래스에 정의된 타입 매개변수와 지네릭 메서드에 정의된 타입 매개변수는 전혀 별개의 것이다. 이에 주의하자.
* static멤버에는 타입 매개변수를 사용할 수 없지만, 메서드에 지네릭 타입을 선언하고 사용하는 것은 가능하다.
* 이 타입 매개변수는 메서드 내에서만 지역적으로 사용될 것이므로 메서드가 static이건 아니건 상관이 없다.
* 대부분의 경우 컴파일러가 타입을 추정할 수 있기 때문에 타입 변수에 타입을 생략 가능하다.
* 대입된 타입을 생략할 수 없는 경우에는 참조변수나 클래스 이름을 생략할 수 없다.
```java
System.out.println(<Fruit>makeJuice(fruitBox)); // 에러. 클래스 이름 생략불가
System.out.println(this.<Fruit>makeJuice(fruitBox)); // OK.
System.out.println(Juicer.<Fruit>makeJuice(fruitBox)); // OK.
```

#### 지네릭 타입의 형변환
```java
import java.util.Optional

// static 상수 EMPTY에 비어있는 Optional 객체를 생성해서 저장했다가 empty()를 호출하면 EMPTY를 형변환해서 반환한다.
public final class Optional<T> {
  private static final Optional<?> EMPTY = new Optional<>();
  private final T value;
  ...
  public static<T> Optional<T> empty() {
    Optional<T> t = (Optional<T>) EMPTY;
    return t;
  }
  ...
}


Optional<?> EMPTY = new Optional<?>();  // 에러. 미확인 타입의 객체는 생성불가
Optional<?> EMPTY = new Optional<Object>();  // OK.
Optional<?> EMPTY = new Optional<>();  // OK. 위의 문장과 동일


Optional<?> wopt = new Optional<Object>();
Optional<Object> oopt = new Optional<Object>();

Optional<String> sopt = (Optional<String>)wopt; // OK. 형변환 가능
Optional<String> sopt = (Optional<String>)oopt; // 에러. Optional<Object>는 Optional<T>로 형변환이 불가능하다.

// Optional<Object>를 Optional<String>으로 직접 형변환하는 것은 불가능하다.
// 하지만, 와일드 카드가 포함된 지네릭 타입으로 형변환하면 가능하다.
// 대신 확인되지 않은 타입으로의 형변환이라는 경고가 발생한다.
```

#### 지네릭 타입의 제거
* 컴파일러는 지네릭 타입을 이용해서 소스파일을 체크하고 필요한 곳에 형변환을 넣어준다. 그리고 지네릭 타입을 제거한다.
* 즉, 컴파일된(*.class)에는 지네릭 타입에 대한 정보가 없다. (지네릭이 도입되기 이전의 소스 코드와의 호환성을 유지하기 위해)
* 지네릭 타입의 제거 과정
1. 지네릭 타입의 경계(bound)를 제거한다
2. 지네릭 타입을 제거한 후에 타입이 일치하지 않으면, 형변환을 추가한다.
