package com.devguide.tomato;

public class NodeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<String> stringList = new LinkedList<>();
//        LinkedList<Integer> integerList = new LinkedList<>();

        System.out.println("[stringList]");
        System.out.println();
        System.out.println("add 4, 3, 2, 1");
        stringList.add("4");
        stringList.add("3");
        stringList.add("2");
        stringList.add("1");

        System.out.println(stringList);
        System.out.println();

        System.out.println("remove second node");
        String s1 = (String) stringList.remove(1);
        System.out.println("removed node : " + s1);
        System.out.println(stringList);
        System.out.println();
        String s2 = (String) stringList.get(1);
        System.out.println("second node is : "+s2);

	}

}
