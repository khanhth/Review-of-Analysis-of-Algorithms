public class SortAnalyzer {
    private int exchanges;
    private int compares;

    public SortAnalyzer() {

    }

    public int getCompares() {
        return compares;
    }

    public int getExchanges() {
        return exchanges;
    }

    public void exchange() {
        exchanges++;
    }

    public void compare() {
        compares++;
    }

    public int ops() {
        return exchanges + compares;
    }

    public void refresh() {
        exchanges = 0;
        compares = 0;
    }
}
