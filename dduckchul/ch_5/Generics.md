# 제네릭 클래스 선언
  * 제네릭 타입은 클래스와 메서드에 선언할 수 있음
  ```java
    class Box<T>{
        T item;
        void setItem(T item) {this.item = item};
        T getItem(){return item};
    }
  ```
  * 기호는 아무거나 쓸수 있지만 상황에 맞게 의미 있는 문자 쓰면 좋겠지..?
  * Box 안에 String만 넣을거면 클래스에 Box<String> 처럼 선언해도 되는데 그럼 진짜 스트링만 들어감..
  * 컴파일 하고나면 Box<String> 이든 Box<Integer>든 사라짐, 즉 컴파일 이후에는 똑같은 Box오브젝트인거임
  ```java
    class Box<T>{
        static T item; // Error
        static void setItem(T item) {this.item = item}; // Error
    }
  ```
  * 위 처럼 static 에 T를 넣을 순 없음 왜냐면 static은 모든 객체에 동일하게 동작해야하므로..?    
  * 제네릭 클래스의 객체 생성 할때 타입을 일치시켜줘야됨, Apple이 Fruit의 자손이여도 아래와 같이하면 에러남
  * 다만 두 객체가 상속관계에 있으면 괜찮음..
  ```java
    Box<Apple> appleBox = new Box<Apple>(); // OK
    Box<Fruit> appleBox = new Box<Apple>(); // Error
    Box<Apple> appleBox = new FruitBox<Apple>(); // FruitBox가 Box의 자식 관계라면 OK
  ```

# 제한된 제네릭 클래스
  * 타입 매개변수에 지정할 수 있는 타입의 종류를 제한하는 방법..? 
    - 제네릭 타입에 Extends를 사용하면 특정 타입만 넣을 수 있게 제한 가능
  * 객체가 아니라 interface를 구현할때도 extends로 사용함
  * extends와 interface 구현해야하는것 둘다 조건일떄는 &로 사용한다
  ```java
    class FruitBox<T extends Fruit & Eatable> extends Box<T>() // Example
  ```
# 와일드 카드
  * static 메서드에서는 제네릭 타입 <T> 를 사용할 수 없으므로, 타입을 정해야됨
    - 그런데 타입을 정하면 메서드를 다른 타입에선 못쓰니까 여러개 메서드를 정의..?
    - 그런데 또 여기서 타입 다르게 여러개를 메서드로 만들어봤자 컴파일단계에서 제네릭 사라짐 
    - 오버로딩이 아니라 메서드 중복 정의
  * 이떄 쓸수 있는게 와일드 카드 / 기호 ?로 표기한다.
    - ? 만으로는 T 처럼 Object 같이 됨
    - <? extends T> T와 그 자손들만 가능, <? super T> T와 그 조상들만 가능, <?> <? extends Object>와 동일 
    - <? super T>의 예제는 Collections.sort()로 봐볼것
    ```java
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        list.sort(null);
    }
    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);
    }
    ```

# 제네릭 메서드
  * 메서드 선언부에 제네릭 타입이 선언된 메서드, 반환 타입 바로 앞에 선언해주면 됨
  * 제네릭 메서드는 제네릭 클래스가 아닌곳에서도 선언할 수 있다.
  ```java
  class FruitBox<T>{
    public static <T> void sort(List<T> list, Comparator<? super T> c) {
  }
  ```
  * 위의 클래스에 선언된 <T>와 메서드에 선언된 <T>는 모양만 같을뿐 전혀 다른것
  * 클래스에서는 static에서 T를 사용할 수 없었지만 여기서는 <T>를 따로 받아서 쓰기때문에 쓸수 있는 것

# 제네릭 타입 형변환
  * 제네릭타입 <-> 논제네릭 타입간 형변환은 가능 (다만 경고 발생)
  * 서로다른 유형이 있는 제네릭 타입간 형변환
  ```java
  Box<Object> objbox = null
  Box<String> strbox = null
  objbox = (Box<Object>)strbox // 불가능
  strbox = (Box<String>)objbox // 불가능

  Box<? extends Object> wbox = new Box<String> // 가능
  Box<? extends Object> obox = null;
  Box<String> strbox = (Box<String>)obox; // 가능, 미확인 타입으로 형변환 경고
  ```

