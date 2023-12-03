public class OffByN implements CharacterComparator{
    private int N;
    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int a = x - y;
        if (a == 1 || a == -1) {
            return true;
        }
        return false;
    }
}
