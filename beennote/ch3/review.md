# 모든 객체의 공통 메서드

Object에서 final이 아닌 메서드(equals, hashCode, toString, clone, finalize)는 모두 재정의(overriding)를 염두에 두고 설계된 것이라 재정의시 지켜야 하는 일반 규약이 명확히 정의되어 있다.

## 아이템10 / equals는 일반 규약을 지켜 재정의하라

#### 1) equals를 재정의하지 않는 것이 최선인 상황
1. 각 인스턴스가 본질적으로 고유하다.(값을 표현하는 게 아니라 동작하는 개체를 표현하는 클래스가 해당 ex) Thread)
2. 인스턴스의 '논리적 동치성(logical equality)'을 검사할 일이 없다.
3. 상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어맞는다.
4. 클래스가 private이거나 package-private이고 equals 메서드를 호출할 일이 없다.

#### 2) equals를 재정의해야 할 때
1. 객체 식별성(object identity; 두 객체가 물리적으로 같은가)이 아니라 논리적 동치성을 확인해야 하는데, 상위 클래스의 equals가 논리적 동치성을 비교하도록 재정의되지 않았을 때. (Integer와 String 같은 주로 값 클래스)
2. 값 클래스라 해도 인스턴스가 둘 이상 만들어지지 않는 인스턴스 통제 클래스 또는 Enum은 equals를 재정의하지 않아도 된다.

#### 3) equals 메서드 재정의할 때 반드시 따라야 하는 일반 규약
1. 반사성(reflexivity): 객체는 자기 자신과 같아야 한다.
2. 대칭성(symmetry): 두 객체는 서로에 대한 동치 여부에 똑같이 답해야 한다.
3. 추이성(transitivity): 첫 번째 객체와 두 번째 객체가 같고, 두 번째 객체와 세 번째 객체가 같다면, 첫 번째 객체와 세 번째 객체도 같아야 한다는 뜻이다.
4. 일관성(consistency): 두 객체가 같다면 (어느 하나 혹은 두 객체 모두가 수정되지 않는 한) 앞으로도 영원히 같아야 한다는 뜻이다.
5. null-아님: 모든 객체가 null과 같지 않아야 한다는 뜻이다. 실수로 NullPointerException을 던지는 경우도 허용하지 않는다.

#### 4) 양질의 equals 메서드 단계별 구현 방법
1. == 연산자를 사용해 입력이 자기 자신의 참조인지 확인한다. 자기 자신이면 true를 반환한다. 단순 성능 최적화용.
2. instanceof 연산자로 입력이 올바른 타입인지 확인한다.
3. 입력을 올바른 타입으로 형변환한다.
4. 입력 객체와 자기 자신의 대응되는 '핵심' 필드들이 모두 일치하는지 하나씩 검사한다.

#### 5) equals 재정의시 주의사항
1. equals를 재정의할 땐 hashCode도 반드시 재정의하자.
2. 필드들의 동치성만 검사해도 equals 규약을 어렵지 않게 지킬 수 있으니 너무 복잡하게 해결하려 들지 말자.
3. Object 외의 타입을 매개변수로 받는 equals 메서드는 선언하지 말자. (@Override 어노테이션 사용하면 컴파일 되지 않도록 예방 가능)

#### 핵심 정리
꼭 필요한 경우가 아니면 equals를 재정의하지 말자. 많은 경우에 Object의 equals가 비교를 정확히 수행해준다. 재정의해야 할 때는 그 클래스의 핵심 필드 모두를 빠짐없이, 다섯 가지 규약을 확실히 지켜가며 비교해야 한다.


## 아이템11 / equals를 재정의하려거든 hashCode도 재정의하라

#### 1) hashCode 재정의에 관한 규약
1. equals 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행되는 동안 그 객체의 hashCode 메서드는 몇 번을 호출해도 일관되게 항상 같은 값을 반환해야 한다.
2. equals(Object)가 두 객체를 같다고 판단했다면, 두 객체의 hashCode는 똑같은 값을 반환해야 한다.
* hashCode 재정의를 잘못했을때 크게 문제가 되는 조항이며 논리적으로 같은 객체는 같은 해시코드를 반환해야 한다.
* ex) HashMap은 해시코드가 다른 엔트리끼리는 동치성 비교를 시도조차 하지 않도록 최적화 되어 있다.
3. equals(Object)가 두 객체를 다르다고 판단했더라도, 두 객체의 hashCode가 서로 다른 값을 반환할 필요는 없다. 단, 다른 객체에 대해서는 다른 값을 반환해야 해시테이블의 성능이 좋아진다.

#### 2) hashCode 재정의 예시

``` java
// 전형적인 hashCode 메서드 - 최첨단은 아니지만 자바 플랫폼 라이브러리가 사용한 방식과 견줄만하다.
@Override public int hashCode() {
    int result = Short.hashCode(areaCode);
    result = 31 * result + Short.hashCode(prefix);
    result = 31 * result + Short.hashCode(lineNum);
    return result;
}

// Object 클래스의 해시코드를 계산해주는 hash 메서드 - 속도가 느리므로 성능에 민감하지 않은 상황에서만 사용하자.
@Override public int hashCode() {
    return Objects.hash(lineNum, prefix, areaCode);
}

// 캐싱하는 방식 - 지연 초기화 전략을 사용.
private int hashCode; // 자동으로 0으로 초기화된다.

@Override public int hashCode() {
    int result = hashCode;
    if (result == 0) {
       result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        hashCode = result;
    }
    return result;
}
```

#### 3) hashCode 재정의시 주의사항
1. 성능을 높인답시고 해시코드를 계산할 때 핵심 필드를 생략해서는 안된다.
2. hashCode가 반환하는 값의 생성규칙을 API 사용자에게 자세히 공표하지 말자.

#### 핵심 정리
equals를 재정의 할 때는 hashCode도 반드시 재정의해야 한다. 서로 다른 인스턴스라면 되도록 해시코드도 서로 다르게 구현해야 한다.

## 아이템12 / toString을 항상 재정의하라

1. Object의 기본 toString 메서드 return 값은 '클래스_이름@16진수로_표시한_해시코드'이다.
2. 모든 하위 클래스에서 이 메서드를 재정의하라는 toString의 규약이 있다.
3. toString 재정의시 그 객체가 가진 주요 정보 모두를 반환하는 게 좋다.
4. 포맷을 문서화할지 안할지는 선택이며 무엇이든 의도를 명확히 밝혀야 한다.
5. toString이 반환한 값에 포함된 정보를 얻어올 수 있는 API를 제공해야 한다. (안그러면 클라이언트가 직접 파싱해야해서 성능이 나빠짐)

#### 핵심 정리
상위 클래스에서 이미 알맞게 재정의한 경우는 제외하고 모든 구체 클래스에서 Object의 toString을 재정의하자.

## 아이템13 / clone 재정의는 주의해서 진행하라

Cloneable을 구현하는 것만으로는 외부 객체에서 clone 메서드를 호출할 수 없다.(Object에 clone 메서드 선언이 protected여서)
Cloneable 인터페이스는 clone의 동작 방식을 결정한다.
배열을 복제할 때는 배열의 clone 메서드를 사용하라고 권장한다.
Cloneable을 이미 구현한 클래스를 확장한다면 어쩔 수 없이 clone을 재정의 해야하지만, 그렇지 않은 상황에서는 복사 생성자와 복사 팩터리라는 더 나은 객체 복사 방식을 제공할 수 있다.

#### 핵심 정리
배열만은 clone 메서드 방식이 가장 깔끔한 복제 방법이지만, 기본 원칙은 '복제 기능은 생성자와 팩터리를 이용하는 게 최고'라는 것이다.

## 아이템14 / comparable을 구현할지 고려하라

Comparable을 구현했다는 것은 그 클래스의 인스턴스들에는 자연적인 순서가 있으며 검색, 극단값 계산, 자동 정렬되는 컬렉션 관리도 쉽게 할 수 있음을 뜻한다.

#### 1) compareTo 메서드 재정의할 때 반드시 따라야 하는 일반 규약
* equals와 비슷하며 compareTo 메서드로 수행한 동치성 테스트의 결과가 equals와 같아야 한다는 권고 규약이 있다.(정렬된 컬렉션들이 동치성을 비교할 때 equals 대신 compareTo를 사용하기 때문)

#### 2) compareTo 메서드 재정의하기
1. 자바7부터 관계연산자를 사용하는 이전 방식은 추천하지 않는다.
2. 자바8에서는 Comparator 인터페이스를 통해 compare 메서드를 사용할 수 있다.(수많은 보조 생성 메서드들로 중무장 하고 있음)
