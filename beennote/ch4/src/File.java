public class File implements Entry {

	private final String name;
	private final int size;

	private File(String name, int size) {
		this.name 	= name;
		this.size 	= size;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public void printList(String prefix) {
		System.out.println(prefix + "/" + this);
	}

	public void printList() {
		printList("");
	}

	public String toString() {
		return getName() + " (" + getSize() + ")";
	}

}
