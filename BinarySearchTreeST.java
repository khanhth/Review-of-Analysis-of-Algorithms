import java.util.Comparator;
import java.lang.Math;

public class BinarySearchTreeST {
	// inner data structure
	private class Node {
		private Comparable item;
		private int value;
		private int depth = 0;
		private Node left;
		private Node right;
		private BSTPrinter printer;
		
		public Node(Comparable item, int value) {
			this.item = item;
			this.value = value;
		}
	}

	private Node root;

	public BinarySearchTreeST() {
    	this.printer = new BSTPrinter();
	}


	// insert new node to the tree
	public void insert(Comparable item, int value) {
		Node node = new Node(item, value);
		root = insert(node, root);
	}

	private Node insert(Node newNode, Node node) {
		if (node == null) return newNode;
		newNode.depth++;

		if (less(newNode.item, node.item)) node.left = insert(newNode, node.left);
		else if (less(node.item, newNode.item)) node.right = insert(newNode, node.right);
		return node;
	}

	public Node get(Comparable item) {
		return get(item, root);
	}

	private Node get(Comparable item, Node node) {
		if (node == null) return null;
		if (less(item, node.item)) return get(item, node.left);
		else if (less(node.item, item)) return get(item, node.right);
		else return node;
	}

	// public void delete(Comparable value) {
	// 	delete(root, value);
	// }

	// private void delete(Node node, Comparable value) {
	// 	if (equals(node.value, value) {
	// 		// complicated
	// 	} elseif (less(value, node.value)) delete(node.left, value);
	// 	else delete(node.right, value);
	// }

	private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean equals(Comparable v, Comparable w) {
        return v.compareTo(w) == 0;
    }


    public void print() {
    	printer.print();
    }

    private void print(Node node) {
    	if (node == null) return;
		
		if (node.left != null) print(node.left);

		if (node == root) {
			System.out.printf("[%s | %s]", node.item, node.value);			
		} else {
			System.out.printf("(%s | %s)", node.item, node.value);
		}

    	if (node.right != null) print(node.right);
    }

    public static void main(String[] args) {
    	BinarySearchTreeST bst = new BinarySearchTreeST();

		bst.insert("G", 100);
    	bst.insert("C", 15);
    	bst.insert("K", 15);

		System.out.printf("root node's depth is: %s\n", bst.root.depth);
    	System.out.printf("G-node's depth is: %s\n", bst.get("G").depth);
    	System.out.printf("C-node's depth is: %s\n", bst.get("C").depth);
    	System.out.printf("K-node's depth is: %s\n", bst.get("K").depth);

    	

    	// bst.print();

    	// System.out.printf("G is: %s\n", bst.get("G").value);
    	// System.out.printf("A is: %s\n", bst.get("A"));

    	// bst.insert("A", 1);
    	// System.out.printf("A is: %s\n", bst.get("A").value);

    	// System.out.printf("A is: %s\n", bst.get("A").value);
    }
}
