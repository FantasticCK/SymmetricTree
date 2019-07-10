package com.CK;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(4);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node6 = new TreeNode(4);
        TreeNode node9 = new TreeNode(8);
        TreeNode node10 = new TreeNode(9);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node6 = new TreeNode(4);
        TreeNode node13 = new TreeNode(9);
        TreeNode node14 = new TreeNode(8);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node4.left = node9;
        node4.right = node10;
        node6.left = node13;
        node6.right = node14;

        root.printTree();
        Solution solution = new Solution();
        System.out.println(solution.isSymmetric(root));

    }
}

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        int height = this.height(root), resLength = 1 << height;
        List<TreeNode> bfArray = new ArrayList<>();
        List<TreeNode> res = new ArrayList<>();
        bfArray.add(root);
        traverseBF(bfArray, res, resLength);
        for (int r = 0; r < height; r++) {
            if (!this.subArrayIfSymmetric(res, (1 << r) - 1, (1 << (r + 1)) - 2))
                return false;
        }
        return true;
    }

    private void traverseBF(List<TreeNode> bfArray, List<TreeNode> res, int resLength) {
        while (res.size() < resLength) {
            TreeNode node = this.shiftArray(bfArray);
            res.add(node);
            if (node.left != null) bfArray.add(node.left);
            else bfArray.add(new TreeNode(-1));
            if (node.right != null) bfArray.add(node.right);
            else bfArray.add(new TreeNode(-1));
            traverseBF(bfArray, res, resLength);
        }
    }

    private TreeNode shiftArray(List<TreeNode> arr) {
        TreeNode res = new TreeNode(0);
        if (!arr.isEmpty()) {
            res = arr.get(0);
            arr.remove(0);
        }
        return res;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    private boolean subArrayIfSymmetric(List<TreeNode> arr, int s, int e) {
        int mid = e - (e - s) / 2;
        for (int i = mid; i <= e; i++) {
            if (arr.get(i).val != arr.get(s + e - i).val)
                return false;
        }
        return true;
    }
}

//Recursive
class Solution1 {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }
}

class Solution2 {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}