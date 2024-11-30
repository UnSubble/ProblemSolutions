import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.Scanner;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    int res = 0;
    
    public int getResult() {
        //implement this
        return res;
    }

    public void visitNode(TreeNode node) {
        //implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
        res += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private static final long CONST = 1000000007L;
    long res = 1L;

    public int getResult() {
        return (int)res;
    }

    public void visitNode(TreeNode node) {
        if (node.getColor() == Color.RED) {
            res *= node.getValue();
            res %= CONST;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.RED) {
            res *= leaf.getValue();
            res %= CONST;
        }
    }
}

class FancyVisitor extends TreeVis {
    int nonLeafNode = 0;
    int greenLeafNode = 0;
    
    public int getResult() {
        return Math.abs(nonLeafNode - greenLeafNode);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0)
            nonLeafNode += node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.GREEN)
            greenLeafNode += leaf.getValue();
    }
}

public class Solution {
    private static int[] values;
    private static Color[] colors;
    private static final Map<Integer, List<Integer>> edges = new HashMap<>();

    static Tree init(int target, int depth, int last) {
        if (edges.get(target).size() == 1) {
            return new TreeLeaf(values[target - 1], colors[target - 1], depth);
        }
        TreeNode node = new TreeNode(values[target - 1], colors[target - 1], depth);
        for (int newTarget : edges.get(target)) {
            if (newTarget != last)
                node.addChild(init(newTarget, depth + 1, target));
        }
        return node;
    }

    public static Tree solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        values = new int[n];
        colors = new Color[n];
        for (int i = 0; i < n; i++)
            values[i] = scanner.nextInt();
        for (int i = 0; i < n; i++)
            colors[i] = scanner.nextInt() == 0 ? Color.RED : Color.GREEN;
        for (int i = 0; i < n - 1; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (!edges.containsKey(x))
                edges.put(x, new ArrayList<Integer>());
            edges.get(x).add(y);
            if (!edges.containsKey(y))
                edges.put(y, new ArrayList<Integer>());
            edges.get(y).add(x);
        }
        scanner.close();
        return init(1, 0, -1);
    }


    public static void main(String[] args) {
      	Tree root = solve();
		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
      	ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
      	FancyVisitor vis3 = new FancyVisitor();

      	root.accept(vis1);
      	root.accept(vis2);
      	root.accept(vis3);

      	int res1 = vis1.getResult();
      	int res2 = vis2.getResult();
      	int res3 = vis3.getResult();

      	System.out.println(res1);
     	System.out.println(res2);
    	System.out.println(res3);
	}
}