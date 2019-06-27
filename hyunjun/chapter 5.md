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

# Item 28. 배열보다는 리스트를 사용하라
## 


