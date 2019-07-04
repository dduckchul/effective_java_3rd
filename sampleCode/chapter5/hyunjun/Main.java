public class Main {

	public static void main(String[] args) {
		
		
		LinkedList<String> stringList = new LinkedList<String>();
		
		System.out.println("empty");
		System.out.println(stringList);
		System.out.println();
		
		stringList.add("1");
		stringList.add("2");
		stringList.add("3");
		stringList.add("4");
		stringList.add("5");
		stringList.add("6");
		stringList.add("7");
		
		System.out.println(stringList);
		System.out.println();
		
		String s1 = (String) stringList.remove(1);
		System.out.println("removed node : " + s1);
		System.out.println(stringList);
		System.out.println();
		
		String s2 = (String) stringList.get(1);
		System.out.println("second node is : "+s2);
		System.out.println(stringList);
		System.out.println();
		
		
	}
	

}
