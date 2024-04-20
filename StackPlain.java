public class StackPlain {

  private class Node {
    public String item;

    public Node next;

    public Node(String item) {
      this.item = item;
    }
  }

  private int size;

  private Node first;

  public StackPlain() {

  }

  public void push(String item) {
    Node oldFirst = first;
    first = new Node(item);
    first.next = oldFirst;
    size++;
  }

  public String pop() {
    if (isEmpty()) return null;

    String item = first.item;
    first = first.next;
    size--;

    System.out.printf("[pop] Item %s is removed from stack.\n", item);

    return item;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void print() {
    if (first == null) {
      System.out.print("[print] Stack is empty!\n");
      return;
    }

    System.out.printf("[print] Stack: %s", first.item);

    Node next = first.next;
    while (next != null) {
      System.out.printf(", %s", next.item);
      next = next.next;
    }

    System.out.print(".\n");
  }

  public static void main(String[] args) {
    StackPlain st = new StackPlain();

    st.push("1");
    st.push("2");
    st.print();

    st.push("3");
    st.print();

    st.pop();
    st.print();

    st.push("3");
    st.print();

    st.pop();
    st.pop();
    st.pop();
    st.pop();
    st.pop();

    st.print();
  }
}
