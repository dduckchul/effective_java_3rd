# 3장 모든 객체의 공통 메서드

## Item 10. equals는 일반 규약을 지켜 재정의 하라

## Item 11. eqauals를 자정의 하려거든 hashCode도 재정의하라

## Item 12. clone 재정의는 주의해서 진행하라

## Item 13. comparable을 구현할지 고려하라



String s = "Here " + "is " + "samples";
JDK 1.5 일 경우 컴파일인 경우에는 다음과 같이 컴파일 된다.
String s = (new StringBuilder ("Here is")).append("samples").toString();
하지만 for문을 돌게된다면 계속 객체를 생성해야하므로 StringBuilder를 사용하는게 좋음


재정의한(Override) return 타입은 상위클래스의 return 타입의 하위 
