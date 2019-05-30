# 아이템 15 클래스와 멤버의 접근권한을 최소화하라
  * 잘 설계된 컴포넌트 : 내부 구현을 완벽히 숨겨 구현과 API를 분리
  * 고급진 용어로 정보 은닉, 캡슐화 
    * 다른 컴포넌트와 소통할떄는 API통해서만..
  * 자바의 정보은닉(캡슐화)
    * 접근 제어자 사용 (private, protected, public)
  
  * 기본 원칙 : 모든 클래스 / 멤버의 접근 가능성 좁혀라
    * public 으로 선언한 것들은 끝까지 유지 보수 / 관리 해줘야함
    * private 로 선언하는 것들은 아무렇게나 빼고 추가해도됨

  * private : 멤버 선언한 톱레벨 클래스에서만 접근 가능
  * package-private : 멤버 소속된 패키지 안의 모든 클래스에서 접근가능
  * protected : package-private + 하위 클래스에서도 접근가능
  * public : 모든곳에서 접근 가능

  * 코드 테스트 용으로 접근범위 넓히지말것.
  * public 클래스의 필드는 public으로 만들지 말것
    * 외부에서 접근가능 하므로, 값을 담는것 제한 할 수 없음.
    * 필드가 수정될떄 Lock 을 걸수 없음, 쓰레드 safe 하지 않다.
  * 배열은 크기가 0이 아닌이상 모두 변경가능 
    * public static final [] 이런거 하지마..
    * 접근자 메서드도 제공하지마..
```java
// 하지마세요..
public static final Thing[] Values = {0,1};
// 대신 이렇게 1
private static final Thing[] PRIVATE_VALUES = {0,1};
public static final List<Thing> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
// 대신 이렇게 2
private static final Thing[] PRIVATE_VALUES = {0,1};
public static final Thing[] values() {
    return PRIVATE_VALUES.clone();
}
```
  * 자바 9 에서는 모듈을 도입, 암묵적 접근 수준 추가됨
    * 클래스의 집합 : 패키지 / 패키지의 집합 : 모듈
    * 패키지중 공개할것을 module-info.java에 선언
      * 모듈 사용할시 클래스를 외부에 공개 안해도 모듈 시스템 안에서는 자유롭게 접근 가능 
      * public 멤버라도 여기 파일에 선언 안하면 접근 못함
    * 모듈의 JAR파일을 모듈 경로에 넣지않고 클래스 패스에 두면 모듈아닌것처럼 동작함.. 주의할것..
