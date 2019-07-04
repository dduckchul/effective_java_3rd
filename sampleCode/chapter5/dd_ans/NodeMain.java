public class NodeMain {
    public static void main(String[] args) {
        LinkedList<String> stringList = new LinkedList<>();
        LinkedList<Integer> integerList = new LinkedList<>();

        stringList.add("4");
        stringList.add("3");
        stringList.add("2");
        stringList.add("1");

        System.out.println(stringList);
        System.out.println();

        String s1 = stringList.remove(1);
        System.out.println("removed node : " + s1);
        System.out.println(stringList);
        System.out.println();
        String s2 = stringList.get(1);
        System.out.println("second node is : "+s2);

        integerList.add(1);
        integerList.add(3);
        integerList.add(5);
        integerList.add(7);

        Integer i1 = integerList.get(1);
        System.out.println("second node is : "+i1);
        System.out.println(integerList);
        System.out.println();

    }
}
