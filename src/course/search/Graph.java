package course.search;

import java.util.LinkedList;
import java.util.List;

class Graph {
    private int[] next;
    private int[] dest;
    private int[] value;
    private int[] head;
    private int[] path;
    private int nextElement = 1;
    private int n;

    public Graph(int n) {
        this.n = n;
        next = new int[n * (n - 1) + 1];
        dest = new int[n * (n - 1) + 1];
        value = new int[n * (n - 1) + 1];
        head = new int[n];
        path = new int[n];
    }

    public void add(int v, int u, int value) {
        next[nextElement] = head[v];
        dest[nextElement] = u;
        this.value[nextElement] = value;
        head[v] = nextElement++;
    }

    public int getMinDistance(int from, int to) {
        int[] d = new int[n];
        boolean[] m = new boolean[n];

        for (int i = 0; i < n; i++) {
            d[i] = -1;
        }
        d[from] = 0;
        path[from] = from;
        while (true) {
            int v = -1;
            for (int i = 0; i < n; i++) {
                if ((!m[i]) && (d[i] != -1) && ((v == -1) || (d[i] < d[v]))) {
                    v = i;
                }
            }
            if ((v == -1) || (v == to)) {
                break;
            }
            int e = head[v];
            while (e != 0) {
                int u = dest[e];
                if ((!m[u]) && ((d[u] == -1) || (d[v] + value[e] < d[u]))) {
                    d[u] = d[v] + value[e];
                    path[u] = v;
                }
                e = next[e];
            }
            m[v] = true;
        }
        return d[to];
    }

    public List<Integer> getLastPath(int from, int to) {
        LinkedList<Integer> result = new LinkedList<>();
        result.addFirst(to);
        while (to != from) {
            to = path[to];
            result.addFirst(to);
        }
        return result;
    }
}
