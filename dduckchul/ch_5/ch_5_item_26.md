# 아이템 26. 로 타입은 사용하지 말라
* 클래스, 인터페이스 선언에 타입 매개변수가 쓰이면 
  * 제네릭 클래스
  * 제네릭 인터페이스
    * Ex) List<E>
* 위 두개를 통틀어서 제네릭 타입이라고 씀
  * 각각의 타입은 매개변수 화 타입을 정의함 Ex) SomeClass<Type>
* 제네릭 타입을 하나 정의하면 그에 딸린 RawType도 함께 정의됨
  * 타입 매개변수를 사용하지 않을때 (이전코드와 호환 위함)
  * Ex) List<E>의 RawType은 List

* 그러나 로 타입을 사용하면 안됨
  * why..? 제네릭의 장점인 안전성, 표현력 모두 잃어버려...

* List와 List<Object>의 차이..?
  * 표현의 차이이긴 하지만 List는 난 제네릭 따위 안씀 vs List<Object>는 모든 오브젝트 허용함
  * 명시적인 표현의 차이가 있슴
    * 파라미터로 List를 받는 메서드에 List<String>은 들어감
    * 파라미터로 List<Object>는 안들어감

* 비한정적 와일드카드 타입 - 제네릭 타입을 쓰고 싶지만 실제 변수가 뭔지 신경 안쓰고싶을때 ?
  * Set<E> -> Set<?>
  * 로 타입 컬렉션에는 아무 원소나 넣을수 있으니 타입 불변식 훼손 쉬움
  * 위와같이 Set<?>인 경우에는 null 말고 어떤 원소도 안들어감

* 예외 두가지
  * class 리터럴에서는 raw type으로 
    * List.class (o)
    * List<String>.class(x)
  * instanceof 연산자는 따로 하지 않고 로타입으로..
    * 런타임에서 제네릭의 정보가 지워짐 (컴파일 단계에서만 수행됨)
    * instanceof 연산자는 비한정적 와일드카드 (?) 이외의 매개변수 타입에 적용 안됨
    * 로 타입이나 비한정적이나 instanceof는 똑같이 동작함..

## Formal Type Parameter Naming Convention Use an uppercase single-character for formal type parameter. For example,
* <E> for an element of a collection;
* <T> for type;
* <K, V> for key and value.
* <N> for number
* S,U,V, etc. for 2nd, 3rd, 4th type parameters