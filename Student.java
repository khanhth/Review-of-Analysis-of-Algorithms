import java.util.Comparator;

public class Student {
    public static final Comparator<Student> BY_ID = new ById();
    public static final Comparator<Student> BY_SECTION = new BySection();
    private final int id;
    private final int section;

    public Student(int id, int section) {
        this.id = id;
        this.section = section;
    }

    private static class ById implements Comparator<Student> {
        public int compare(Student v, Student w) {
            return v.id - w.id;
        }
    }

    private static class BySection implements Comparator<Student> {
        public int compare(Student v, Student w) {
            return v.section - w.section;
        }
    }

    public String toString() {
        return String.format("{id: %d, section: %d}", id, section);
    }
}
