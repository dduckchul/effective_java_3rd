# 제네릭
## 자바 제네릭: QnA http://www.angelikalanger.com/GenericsFAQ/JavaGenericsFAQ.html

## Item 26. raw type은 사용하지 말라
### 제네릭으로 선언하면 컴파일러가 자동으로 형변환 구문을 추가해줌
### List<E> --> 제네릭타입, E: 정규타입매개변수, List<String> --> 매개변수화 타입, String: 실제타입매개변수
### raw type은 제네릭 타입에서 매개변수를 다 빼버린 버젼. 하위 버젼과 호환성을 위해서 함께 정의됨.
### 제네릭의 하위타입 규칙 List > List<String>, List<Object> <> List<String>
### 비한정적 와일드카드 <?> 실제 타입 매개변수가 중요하지 않을경우 사용. 가장 범용적인 타입
### raw type과 <?>차이는 checked cast 여부
### <?>에는 null 외에 어떤 매개변수도 넣을수가 없다. ex) List<?> = List<Sting>
### 예외1. 클래스 리터럴에는 매개변수화 타입을 사용할 수 없다. ex> List.class: O, List<String>: X
### 예외2. instance of 구문에 사용하는 것 런타임에는 제네릭타입 정보가 지워지므로 List<E>나 List나 똑같이 동작함
### 따라서 코드를 지저분하게 하는대신 raw type을 써도 됨.
### <?> 비한정와일드카드, <E extends Number> 한정타입 매개변수, <T extends Comparable<T>> 재귀타입한정
### List<? extends Number> 한정적와일드카드 타입, String.class 타입토큰


## Item 27. 비검사 경고를 제거하라
### 경고를 제거 할 수 없지만 안전하다는 확신이 들면 @SuppressWarings를 달아 경고를 숨겨라
### @SuppressWarings가능한 작은 범위에 적용하는것이 좋다. (* 어노테이션은 선언에만 달수 있다.)

## Item 28. 배열보다는 리스트를 사용하라
### 배열은 공변이기 때문에 제네릭을 사용해도 타입안정성을 보장할수없다.
### 따라사 자바에서는 제네릭 배열을 허용하지 않는다.
### * 공변 Sub이 Super의 하위 타입이라면 Sub[]도 Super[]의 하위타입.(공변 함꼐 변한다)
### 타입안정성이 보장 받이 못하는 예 (만약 제네릭 배열이 허용된다고 하면)
#### Object[] objList = new List<String>[1]; objList[0] = new List<Integer>();
### 배열은 실체화가 가능하고 제네릭 타입은 실체화가 불가능하다.
#### 실체화란 런타임에도 자신의 타입을 확인하고 검사한다는 것
#### 배열은 런타임에도 Long배열에 String을 넣을수없지만
#### 제네릭 타입은 런타임시 제네릭 타입정보가 소거된다. (자바하위 호환성을 위한 조치)
#### 따라서 E, List<E>, List<String> 같은 타입을 실체 불가 타입이라고 한다.
#### 단 <?>은 실체화가 가능하나 유용하게 쓰일곳이 거의 없다 (??? raw type으로 실체화가 된다는 말인가 ???)
### 가변인수(ex> String... args)와 제네릭 타입을 함께쓰면 경고가 뜰수있다. 가변인수를 배열로 만드는 과정에서 실체화 불가 경고가 뜸
### class A <T>; T[]; 가 런타임이 되면 T정보가 소거되는데 그럼 뭘로 컴파일이 될까? Object

## Item 29. 이왕이면 제네릭 타입으로 만들라
### 힙오염 매개변수 타입이 매개 변수 타입이 아닌 Object를 바라볼때 생기는것
### classCastException을 발생시킬수있다.
### ex> List Ln = new ArrayList<Number>(); List<String> Ls = In; String s = Ls.get(0);
### 한정타입매개변수 <E extends Number> 타입 매개변수가 Number의 하위클래스임을 보장함

## Item 30. 이왕이면 제네릭 메서드로 만들라
### 제네릭 싱클턴 팩터리: 불변객체를 어러 타입으로 활용할때 매개변수에 맞게 객체의 타입을 바꿔주는 메소드
##### ex> private static applySame<Object> IDENTITY; 
#####      public static <T> applySame<T> identityFunction() {
#####                            return (applySame<T>) IDENTITY;
#####                          }
### 스트림 공부가 필요할듯
### 재귀적 타입한정 타입한정에 자기자긴이 포함된것
### ex) <E extends Comparable<E>> --> 타입 E는 자기자신과 비교할수있다 (Comparable 인터페이스를 상속한 class만 받겠다)
  
## Item 31. 한정적 와일드카드를 사용해 API유연성을 높이라
### 한정적 와이드카드 타입을 적용하는 법칙
#### PECS: producer-extends, consumer-super (생산과 소비를 동시에 할때는 한정적 와이드카드가 의미가 없음)
#### 바나나묶음을 예로들면 소비는 먹는거라는 과일의 공통 기능이 있어 super로 쓰고 생산자는 같이 바나나여나 묶을수있기에 extends를 씀
### 반환타입에는 한정적와일드카드를 쓰면안됨. 클라이언트 코드에도 와일드 카드를 써야하기 떄문에
### 명시적 타입인수 public <E> Set<E> union(Collection<E> arg) --> Union.<Number>union(arg);
### 매가변수(parameter) 인수(argument)차이 매개변수는 선언시 사용된것. 인수는 실제 넘겨진 값
### implements에도 제네릭이 사용가능함
### 타입 매개변수가 한번만 나온다면 와일드카드를 사용하라.
### 와일드카드는<?> 관리 할필요가 없음. (ex 메소드나 클래스 앞에 선언)
### 와일드카드로 선언해서 그객체를 다룰수 없을때는 타입을 알려주는 private도우미 메서드를 선언하면 됨
#### public void swap (List<?> a)의 도우미 메서드는 --> private <E> void swapHelper(List<E> a)
#### 이렇게 하면 도우미 메서드에서는 타입이 E로 확정되에 다룰수 있게됨
#### 이렇게 굳이 메서드를 하나 더 만드는 이유는 도우미메서드의 시그니처는 apiu로 공개하기에 너무 복잡하기 때문(유연성 저하?)
  
## Item 32. 제네릭과 가변인수를 함께쓸 때는 신중하라
### 가변인수를 사용하면(String...) 실체화시 배열이 생기는데 제네릭은 보통 실체화 불가임으로
### 실체화시 생기는 배열에 제네릭 정보를 담을수가 없다. 배열생성시 이미 제네렉 정보는 제거된 상태임으로. 따라서 타입 오류가 발생할수있다.
### 타입안전한(@SafeVarargs) 제네릭 varargs메소드를 판단하는 법
#### 1. varargs 매개변수에 아무것도 저장하지 않는다
#### 2. varargs 배열의 주소가 외부에 노출 되지 않는다(단 @SafeVarags메소드는 괜찮음)
### public <T> T[] toArray(T... args)를 실체화하면 Object[] args 가 만들어짐
### 제네릭정보는 컴파일시 다 지워지기 때문에 모든 객체를 받을수있는 배열이 생성됨
