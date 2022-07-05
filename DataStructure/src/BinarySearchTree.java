
/**
 * @author Yongzhi Xu
 *
 */
public class BinarySearchTree {

//	Binary Search Tree is a node-based binary tree data structure which has the following properties:
//
//		The left subtree of a node contains only nodes with keys lesser than the node¡¯s key.
//		The right subtree of a node contains only nodes with keys greater than the node¡¯s key.
//		The left and right subtree each must also be a binary search tree.
	
	class Tree{
		
		public 	Tree(long val) {
			this.val=val;
		}
		long val;
		Tree lefTree;
		Tree righTree;
	}
	
	
	public static void main(String [] argStrings) {
		new BinarySearchTree();
	}
	
	public BinarySearchTree() {
//		testing example
		Tree samTree = new Tree(13);
		Tree lefTree =new Tree(11);
		lefTree.lefTree = new Tree(10);
		lefTree.righTree = new Tree(13);
		Tree righTree =new Tree(16);
		samTree.lefTree=lefTree;
		samTree.righTree=righTree;
		
		
		System.out.println("Bst testing of this tree shows: "+ isBst(samTree));
		
	}
	
	boolean isBst(Tree root) {
        return isBst(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
	
    /**
     * using recursion to decide if the tree is qualified as binary search tree
     * @param root
     * @param minVal
     * @param maxVal
     * @return
     */
    boolean isBst(Tree root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isBst(root.lefTree, minVal, root.val) && isBst(root.righTree, root.val, maxVal);
    }
	
}
