public class Directory implements Entry {

	private final String name;
	private final ArrayList<Entry> directory;

	private Directory(String name) {
		this.name 		= name;
		this.directory 	= new ArrayList<Entry>();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getSize() {
		int size = 0;
		Iterator<Entry> it = directory.iterator();
		while(it.hasNext()) {
			Entry entry = it.next();
			size += entry.getSize();
		}
		return size;
	}

	@Override
	public void printList(String prefix) {
		System.out.println(prefix + "/" + this);
		Iterator<Entry> it = directory.iterator();
		while(it.hasNext()) {
			Entry entry = (Entry)it.next();
			entry.printList(prefix + "/" + name);
		}

	}

	public void printList() {
		printList("");
	}

	public Entry add(Entry entry) {
		directory.add(entry);
		return this;
	}

	public String toString() {
		return getName() + " (" + getSize() + ")";
	}

}
