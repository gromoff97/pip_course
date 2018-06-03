package course.search;

public class FoundPath {
    private String path;
    private int time;

    public FoundPath(String path, int time) {
        this.path = path;
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public int getTime() {
        return time;
    }
}
