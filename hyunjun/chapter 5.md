# 제네릭

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
##3 * 공변 Sub이 Super의 하위 타입이라면 Sub[]도 Super[]의 하위타입.(공변 함꼐 변한다)
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

  
