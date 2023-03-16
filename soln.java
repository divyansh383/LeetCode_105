/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int[] inOrder;
    int[] preOrder;
    HashMap<Integer,Integer> map;
    TreeNode makeTree(int inStart,int inStop,int preStart,int preStop){
        if(inStart>inStop){return null;}
        TreeNode root=new TreeNode(preOrder[preStart]);
        int rootIndex=map.get(root.val);

        int LST_size=rootIndex-inStart;
        int RST_size=inStop-rootIndex;

        root.left=makeTree(inStart,rootIndex-1,preStart+1,preStart+LST_size);
        root.right=makeTree(rootIndex+1,inStop,preStop+1-RST_size,preStop);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.inOrder=inorder;
        this.preOrder=preorder;
        int n=inorder.length;
        this.map=new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(inorder[i],i);
        }
        return makeTree(0,n-1,0,n-1);
    }
}