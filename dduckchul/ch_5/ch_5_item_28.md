# 아이템 28. 배열보다는 리스트를 사용하라
* 배열과 제네릭 타입의 차이..?
  * Ex) String [] vs List<String>
  * 배열은 공변 Sub[] 은 Super[]의 하위타입
  * 제네릭은 불공변 List<Type1> List<Type2>는 상 하위 타입 가리지 않아
    ``` java
    Object [] objectArr = new Long[1];
    objectArray = "스트링 하면 안됨"; // 런타임 에러
    
    List<Object> ol = new ArayList<Long>();
    o1.add("스트링 하면 안됨") // 컴파일 에러
    ```
  * 배열은 실체화 된다
    * 런타임에도 타입을 확인하여 Excption 발생시켜..
  * 제네릭은 타입 정보가 런타임에서 제거 (타입 검사 안하고 걍 넣음)
  * 제네릭은 실체화가 안되는 실체화 불가 타입임
    * E, List<E>, List<String> 둥은 실체화가 안됨

  * 배열과 제네릭은 함께 쓰지 못하게 막아놨어.. 잘 안어울림
