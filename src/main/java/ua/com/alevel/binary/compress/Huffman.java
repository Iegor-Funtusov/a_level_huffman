package ua.com.alevel.binary.compress;

public class Huffman {

    public final int ALPHABETSIZE;
    public String[] code;
    Tree[] tree;
    int[] weights;

    public Huffman(int size) {
        ALPHABETSIZE = size;
        this.tree = new Tree[size];
        this.weights = new int[size];
        this.code = new String[size];
    }

    private int getLowestTree(int used) {
        int min = 0;
        for (int i = 1; i < used; i++)
            if (tree[i].weight < tree[min].weight) min = i;
        return min;
    }

    public void growTree(int[] data) {
        for (int datum : data) weights[datum]++;
        int used = 0;
        for (int c = 0; c < ALPHABETSIZE; c++) {
            int w = weights[c];
            if (w != 0) tree[used++] = new Tree(c, w, true);
        }
        while (used > 1) {
            int min = getLowestTree(used);
            int weight0 = tree[min].weight;
            Tree temp = new Tree();
            temp.child0 = tree[min];
            tree[min] = tree[--used];
            min = getLowestTree(used);
            temp.child1 = tree[min];
            temp.weight = weight0 + tree[min].weight;
            tree[min] = temp;
        }
    }

    public void makeCode() {
        tree[0].traverse("", this);
    }

    public String coder(int[] data) {
        StringBuilder str = new StringBuilder();
        for (int datum : data) str.append(code[datum]);
        return str.toString();
    }

    public String decoder(String data) {
        StringBuilder str = new StringBuilder();
        while (data.length() > 0) {
            for (int c = 0; c < ALPHABETSIZE; c++) {
                if (weights[c] > 0 && data.startsWith(code[c])) {
                    data = data.substring(code[c].length());
                    str.append((char) c);
                }
            }
        }
        return str.toString();
    }
}
