package Tree_Traversals;

public class TreeTraversal {
	/*
		Purpose: create a Tree and traverse it using Pre-Order, In-Order, and Post-Order
		traversals.
	*/

	public static void main(String[] args) {
		//create test data
		int[] array = {1, 2, 3, 4,5,6,7,8,9,10};
		TreeNode node = TreeNode.createMinimalBST(array);
		//output
		node.print();
		//pre-order
		System.out.println("Pre-Order Traversal:");
		preOrderTraversal(node);
		System.out.println("\n");
		//in-order
		System.out.println("In-Order Traversal:");
		inOrderTraversal(node);
		System.out.println("\n");
		//post-order
		System.out.println("Post-Order Traversal:");
		postOrderTraversal(node);
		System.out.println("\n");
	}

	public static void visit(TreeNode n){
		/*
		Visit can perform whatever operation you need, in this case
		it just prints the node's data
		*/
		System.out.print(n.data+" ");
	}

	public static void preOrderTraversal(TreeNode n){
		//check for null
		if(n != null){
			//visit the root node fist, then its children
			visit(n);
			preOrderTraversal(n.left);
			preOrderTraversal(n.right);
		}
	}

	public static void inOrderTraversal(TreeNode n){
		//check for null
		if(n != null){
			//traverse the left branch, then visit current node, then traverse right branch
			inOrderTraversal(n.left);
			visit(n);
			inOrderTraversal(n.right);
			//NOTE: if performed on a BST, it visits nodes in ascending order
		}
	}

	public static void postOrderTraversal(TreeNode n){
		//check for null
		if(n != null){
			//traverse the left branch, then right branch, then visit the current node
			postOrderTraversal(n.left);
			postOrderTraversal(n.right);
			visit(n);
			//the root should be visited last
		}
	}


}