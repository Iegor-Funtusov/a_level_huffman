package ua.com.alevel.binary.compress;

import ua.com.alevel.binary.data.MakeCode;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    public Tree child0;
    public Tree child1;
    public boolean leaf;
    public int character;
    public int weight;

    public static List<MakeCode> getMakeCodes() {
        return makeCodes;
    }

    public static void initMakeCodes() {
        makeCodes = new ArrayList<>();
    }

    private static List<MakeCode> makeCodes = new ArrayList<>();

    public Tree() {}

    public Tree(int character, int weight, boolean leaf) {
        this.leaf = leaf;
        this.character = character;
        this.weight = weight;
    }

    public void traverse(String code, Huffman h) {
        if (leaf) {
            h.code[character] = code;
            MakeCode makeCode = new MakeCode();
            makeCode.setCharacter((char)character);
            makeCode.setWeight(weight);
            makeCode.setCode(code);
            makeCodes.add(makeCode);
        }
        if ( child0 != null) child0.traverse(code + "0", h);
        if ( child1 != null) child1.traverse(code + "1", h);
    }
}
