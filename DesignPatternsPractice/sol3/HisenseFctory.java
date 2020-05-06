package sol3;

public class HisenseFctory implements Television {
    public TV createTV() {
        return new TV("Hisense");
    }
}
