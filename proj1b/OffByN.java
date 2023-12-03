public class OffByN implements CharacterComparator{
    private int N;
    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int a = x - y;
        if (a == N || a == -N) {
            return true;
        }
        return false;
    }
}
