import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing argument for number of items to display.");
        }

        int max = Integer.parseInt(args[0]);

        RandomizedQueue<String> q = new RandomizedQueue<>(100);

        StringBuilder word = new StringBuilder();
        while (StdIn.hasNextChar()) {
            char c = StdIn.readChar();
//            StdOut.print(c);

            if (c == '\n') {
                q.enqueue(word.toString());
                break;
            }

            if (c == ' ') {
//                StdOut.print(word);
                q.enqueue(word.toString());
                word = new StringBuilder();
                continue;
            }

            word.append(c);
        }

        q.dequeue();
        q.dequeue();
        q.dequeue();

        int i = 0;
        for (String s: q) {
            if (i++ == max) {
                break;
            }
            System.out.printf("%s ", s);
        }
    }
}
