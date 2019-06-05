import java.util.Iterator;
import java.util.ArrayList;

public class Directory extends Entry {
    private String name;                            // 디렉터리의 이름
    private ArrayList directory = new ArrayList();      // 디렉터리 엔트리의 집합
    public Directory(String name) {                  // 생성자
        this.name = name;
    }
    public String getName() {                       // 이름을 얻는다
        return name;
    }
    public int getSize() {                            // 크기를 얻는다
        int size = 0;
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            size += entry.getSize();
        }
        return size;
    }
    public Entry add(Entry entry) {                  // 엔트리의 추가
        directory.add(entry);
        return this;
    }
    protected void printList(String prefix) {           // 엔트리의 일람
        System.out.println(prefix + "/" + this);
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            entry.printList(prefix + "/" + name);
        }
    }
}
