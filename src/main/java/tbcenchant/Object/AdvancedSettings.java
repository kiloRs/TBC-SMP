package psikuvit.tbcenchant.Object;

public class AdvancedSettings {
    private final int limit;

    private final int perLine;

    private final String line;

    public AdvancedSettings(int limit, int perLine, String line) {
        this.limit = limit;
        this.perLine = perLine;
        this.line = line;
    }

    public int getLimit() {
        return this.limit;
    }

    public int getPerLine() {
        return this.perLine;
    }

    public String getLine() {
        return this.line;
    }
}
