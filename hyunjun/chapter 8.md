# 8장 메서드
## Item 49. 매개변수가 유효한지 검사하라
> * 프로그래밍 일반 원칙 : 오류는 가능한 빨리 (발생한 곳에서) 잡아야 한다  
> * public, protected 메서드는 매개변수가 잘못되엇을때 던지는 예외를 문서화 해야한다
   ex> @throws IlegalArgumentException m 이 0보다 작으면 발생한다.   
> * 자바 7에 java.util.Object.requiredNonNull 메서드로 null 검사를 변하게 할 수 있음.
    자바 9에는 범위 검사 메소드도 추가 됨. (checkFromIndexSize, checkFromToIndex, checkIndex)
   ex> Objects.requiredNonNull(strategy, "전략");   
> * Assertion : 단언문 - 조건이 무조건 참이라고 선언하는것. 참이 아니면 AssertError 던짐
    runtime에는 아무 효과도 없다? 검증 필요. 런타임 아니면 확인을 못하는 조건이 있어보임
    > ex> assert a!= null; assert offset >= 0 && offset <= a.length
> * 프로그래밍 일반 원칙 : 메서드가 직접 사용하지는 않으나 나중에 사용하러 저장하는 매개변수는 더 꼼꼼히 검사해야함
    예외가 문제가있는 코드와 멀리 떨어져서 발생하기 때문에 추적하기 어려워진다.
    > ex> [] -> List 로 변환하는 메서드의 경우 [] != null을 검사하지 않으면 나중에 List를 사용할떄 예외가 발생함  
> * 프로그래밍 일반 원칙 : 메서드 몸체가 실행되기 전에 매개변수를 검사하라
  * 예외 : 유효성 검사 비용이 지나치게 크거나, 계산 과정중 암묵적으로 검사가 될때(ex> 비교메소드는 비교할때 대상의 타입을 암묵적으로 검사하게됨)

## Item 50. 적시에 방어적 복사본을 만들라
> * Date는 사용하면 안됨 java8에 LocalDate, LocalTime, LocalDateTime 사용해야됨
> * 
