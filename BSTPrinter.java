public class BSTPrinter {
	private BinarySearchTreeST tree;
	private Node root;
	private int rootHeight;
	
	public BSTPrinter(BinarySearchTreeST tree) {
		this.tree = tree;
		this.root = tree.root();
		this.rootHeight = rootHeight();
	}

	public void print() {
		print(thi.root, null);
	}

	private void print(Node node, Node parentNode) {
		if (parentNode == null) printRootNode(node);
		// else {
		// 	print(node.left, node);
		// 	print(node.right, node);
		// }
	}

	private void printRootNode(Node root) {
		for (int i = 0; i < Math.pow(rootHeight,2) - 1; i++)
			System.out.printf("\t");

		System.out.print(root.item);
	}

	public int rootHeight() {
		if (rootHeight != 0) return rootHeight;

		Node node;
		int countLeft = 0;
		int countRight = 0;
		
		for (node = thi.root; node != null && node.left != null; node = node.left) {
			countLeft++;
		}

		for (node = thi.root; node != null && node.right != null; node = node.right) {
			countLeft++;
		}


		return countLeft > countRight ? countLeft : countRight;
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

		BSTPrinter printer = new BSTPrinter(bst);

		printer.print();
	}


}
