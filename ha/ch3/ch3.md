# 제네릭

## ITEM26 
## ITEM27 
## ITEM28

## ITEM29 이왕이면 제네릭 타입으로 만들라
배열대신 제네릭을 사용하도록 변경하자

* object 기반 stack 클래스를 제네릭 stack으로 변환하는 예제
E 같은 실체화 불가 타입으로는 배열을 만들 수 없으니(new E[10])
방안 1 ) (E) new Object 형변환
방법 2 ) elements 필드타입을 E[]가 아닌 Object[]로 변경

? 힙오염이 뭔지 모르겟다
