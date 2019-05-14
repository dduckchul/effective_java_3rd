# 2장 객체 생성과 파괴

## Item 1. 생성자 대신 정적팩터리 메서드를 고려하라
### 정적 팩터리 매서드 장점
#### 1. 이름을 가질수 있다
#### 2. 인스턴스의 생성을 통제 할수 있다
#### 3. 하위타입의 객체를 반환 할수있다
#### 4. 입력받은 매개변수에 따라 구현체를 바꿀수있다.
#### 5. 정적팩터리메서드를 작성하는 시점에서는 반환할 객체의 클래스가 정의 존재하지 않아도 된다.
### 정적 팩터리 메서드 단점
#### 1. 상속을 위해서는 상위클래스의 생성자에 접근이 가능하여야 하므로 정적팩터리메서드만 있으면 상속이 불가능 하다.
        (이 제약 때문에 상속보다 컴포지션을 이용하도록 유도할수도있고 불변 타입으로 만들려면 이 제약이 필수이므로 장점으로 작용할수도잇음)
#### 2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵다. - 생성자처럼 명확히 드러나는것이 아니므로 사용자가 인스턴스화 할 방법을 찾아야한다.

## Item 2. 생성자에 매개변수가 많다면 빌더를 고려하라


## Item 3. ptivate 생성자나 열거타입으로 싱글턴임을 보증하라


## Item 4. 인스턴스화를 막으려거든 private 생성자를 사용하라



## Item 5. 자원을 직접 명시하지말고 의존 객체 주입을 사용하라


## Item 6. 불필요한 객체 생성을 피하라

## Item 7. 다 쓴 객체참조를 해제하라

## Item 8. finalizer와 cleaner 사용을 피하라

## Item 9. try-finally 보다는 try-resouce를 사용하라
