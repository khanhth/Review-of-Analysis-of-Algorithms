public class SortAnalyzer {
    private int exchanges;
    private int compares;
    private int accesses;

    public SortAnalyzer() {

    }

    public int getAccesses() {
        return accesses;
    }

    public int getCompares() {
        return compares;
    }

    public int getExchanges() {
        return exchanges;
    }

    public void access() {
        accesses++;
    }

    public void access(int inc) {
        accesses += inc;
    }

    public void exchange() {
        exchanges++;
    }

    public void compare() {
        compares++;
    }

    public int total() {
        return exchanges + compares + accesses;
    }

    public void refresh() {
        exchanges = 0;
        compares = 0;
        accesses = 0;
    }
}
