# 아이템 24. 멤버 클래스는 되도록 static으로 만들어라
* 중첩 클래스 (Nested class)
  * 자신을 감싼 바깥 클래스에서 쓰여야 하며, 그외의 쓰임새가 있다면 톱레벨 클래스로 만들어야 한다.
* 중첩 클래스의 종류
  * 정적 멤버 클래스
    * 다른 클래스 안에선언, 바깥 클래스의 멤버에 접근 가능
    * 그 외에는 일반 클래스와 동일함
    * 바깥 클래스와 함께 쓰일때만 유용한 Public 도우미 클래스로 이용하면 좋음 
  * (비정적) 멤버 클래스
    * 정적이 아닌것은 암묵적으로 연결됨
    * 정규화된 this를 사용하면 바깥 인스턴스를 호출 해 버릴수 있음
      * 정규화된 this = Inner.this
    * 주로 어떤 클래스의 인스턴스를 감싸 마치 다른 클래스의 인스턴스처럼 보이게 하는 뷰로 꾸밀때 사용.. (오라클 테이블의 view랑 비슷한듯..?)
      * Map의 구현체들 봐봐.. 
        * HashMap.EntrySet, HashMap.KeySet
        1. 사실은 HashMap의 내부 멤버들을 사용하기 위해 HashMap.EntrySet이라는 별도의 클래스
        2. AbstractSet을 extends해서 구현함, B is A 관계 성립 할수 있다.
          * AbstractSet(부모) - HashMap.EntrySet(자식) 관계 성립. 
        3. AbstractSet은 Set의 구현체이기 떄문에 Set<T>으로 래핑 해줄수있음
        4. 실제 EntrySet은 package-private로 선언해서 캡슐화, EntrySet을 직접 접근방지함
    * 하지만 이렇게 바깥 인스턴스에 접근할 일이 없으면 정적 (static) 으로 만들자
    * 내부적으로는 참조를 하게될것이므로, keySet()은 HashMap을 참조할것.
    * 이렇게 되면 가비지 컬렉션 되지 않을것임 
  * 익명 클래스
    * 이름 없는 클래스, 바깥 클래스의 멤버도 아님
    * 쓰이는 시점에 바로 인스턴스가 만들어짐
    * static 변수들 쓸수 없음 (쓰이는 시점에 인스턴스 되니깐..)
  * 지역 클래스
    * 지역 변수처럼 선언 가능

