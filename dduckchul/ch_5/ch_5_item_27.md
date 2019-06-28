# 아이템 27. 비검사 경고를 제거하라
* 할수 있는 한 모든 비검사 경고를 제거하라, 타입 안정성이 보장된다
  * 런타임에 classCastExcption이 날 일이 없음
* 안전하다고 확신할 수 있으면 @SuppressWarnings("unchecked") 해서 경고를 없에
  * @SuppressWarning을 달았다면 거기에 주석을 추가해서 왜 달았는지 작성해
