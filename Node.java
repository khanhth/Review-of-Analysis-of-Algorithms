// inner data structure
public class Node {
	private Comparable item;
	private int value;
	private int depth = 0;
	private Node left;
	private Node right;
	
	public Node(Comparable item, int value) {
		this.item = item;
		this.value = value;
	}
}