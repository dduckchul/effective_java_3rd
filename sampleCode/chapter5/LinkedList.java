public class LinkedList	{

	// 첫번째 노드를 가리키는 필드
	private Node head;
	private Node tail;
	private int size = 0;

	private class Node {
		// 데이터가 저장될 필드
		private Object data;
		// 다음 노드를 가리키는 필드
		private Node next;

		public Node(Object input) {
			this.data = input;
			this.next = null;
		}

		// 노드의 내용을 쉽게 출력해서 확인해볼 수 있는 기능
		public String toString() {
			return String.valueOf(this.data);
		}
	}
	
	// 리스트의 맨뒤에 노드 추가
	public void add(Object input) {
		Node newNode = new Node(input);
		// 마지막 노드는 자기 자신을 가리킴
		newNode.next = newNode;
		
		// 리스트 size가 0이라면 head에 추가
		if (size == 0) {
			head = newNode;
			tail = newNode;
		}
		
		// tail노드 변경
		tail.next = newNode;
		tail = newNode;
		
		size++;
	}
	
	public int size() {
		return size;
	}

	public Object get(int index) {
		if (index < 0 || index >= size)	{
			throw new RuntimeException("out of index exception");
		}
		Node temp = node(index);
		return temp.data;
	}
	
	Node node(int index) {
		if (index < 0 || index >= size)	{
			throw new RuntimeException("out of index exception");
		}
		Node x = head;
		// Iterator 구현시 iterator로 대체 가능
		for (int i = 0; i < index; i++)
			x = x.next;
		return x;
	}

	public Object remove(int index) {
		if (index < 0 || index >= size)	{
			throw new RuntimeException("out of index exception");
		}
		
		Object returnData = null;
		
		if (index == 0)	{
			returnData = head.data;
			head = head.next;
		}
		
		// k-1번째 노드를 temp의 값으로 지정합니다.
		Node temp = node(index - 1);
		// 삭제 노드를 todoDeleted에 기록해 둡니다.
		Node todoDeleted = temp.next;
		// 삭제 앞 노드의 다음 노드로 삭제 뒤 노드를 지정합니다.
		temp.next = temp.next.next;
		// 삭제된 데이터를 리턴하기 위해서 returnData에 데이터를 저장합니다.
		returnData = todoDeleted.data;
		
		if (todoDeleted == tail) {
			temp.next = temp;
			tail = temp;
		}
		
		size--;
		
		return returnData;
	}	

	@Override
	public String toString() {
		// 노드가 없다면 []를 리턴합니다.
		if (head == null) {
			return "[]";
		}
		// 탐색을 시작합니다.
		Node temp = head;
		String str = "[";
		// 다음 노드가 없을 때까지 반복문을 실행합니다.
		// 마지막 노드는 다음 노드가 없기 때문에 아래의 구문은 마지막 노드는 제외됩니다.
		while (temp.next != temp) {
			str += temp.data + ",";
			temp = temp.next;
		}
		// 마지막 노드를 출력결과에 포함시킵니다.
		str += temp.data;
		return str + "]  size : "+size;
	}

	/*
	 * 자기자신의 Iterator를 비정적 멤버 클래스로 구현
	 * 
	 * @Override
	 * public String toString() { // 노드가 없다면 []를 리턴합니다. if(head == null){ return
	 * "[]"; } // 탐색을 시작합니다. Node temp = head; String str = "[";
	 * 
	 * // 다음 노드가 없을 때까지 반복문을 실행합니다. ListIterator listIterator = listIterator();
	 * while(listIterator.hasNext()){ str += listIterator.next().toString() +
	 * ","; } return str+"]"; }
	 * 
	 * 
	 * public Iterator<E> iterator() { return new MyIterator(); }
	 * 
	 * private class MyIterator<E >implements Iterator<E> {
	 * 
	 * }
	 * 
	 */

}