package tree;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> implements Tree<T>{
	
	@Override
	public void insert(T data) {
		this.root = insert(data, root);
	}
	
	/**
	 * Helper method for inserting recursively
	 * @param data
	 * @param curNode
	 * @return a reference to the new root of the subtree
	 */
	protected TreeNode<T> insert(T data, TreeNode<T> curNode) {
		//TODO: Implement this method
		if(curNode==null) {
			curNode = new TreeNode<T>(data);
			return curNode;
		}
		if(data.compareTo(curNode.data)==0)
			return curNode;
		else if(data.compareTo(curNode.data)>0)
			curNode.right = insert(data, curNode.right);
		else
			curNode.left = insert(data, curNode.left);
		updateHeight(curNode);
		return curNode;
	}

	@Override
	public boolean find(T data) {
		return find(data, root);
	}
	
	private boolean find(T data, TreeNode<T> curNode) {
		//TODO: Implement this method
		if(curNode == null) return false;
		else if(data.compareTo(curNode.data)<0)
			return find(data, curNode.left);
		else if(data.compareTo(curNode.data)>0)
			return find(data, curNode.right);
		else return true;
	}

	@Override
	public void remove(T data) {
		this.root = remove(data, root);
	}
	
	protected TreeNode<T> remove(T data, TreeNode<T> curNode) {
		//TODO: Implement this method
		if(curNode==null)
			return null;
		if(data.compareTo(curNode.data)==0) {
			if(curNode.right==null && curNode.left==null)
				curNode = null;
			else if(curNode.right==null && curNode.left!=null)
				curNode = curNode.left;
			else if(curNode.right!=null && curNode.left==null)
				curNode = curNode.right;
			else {
				curNode.data = findMax(curNode.left);
				curNode.left = remove(findMax(curNode),curNode.left);
			}
		}
		else if(data.compareTo(curNode.data)<0)
			curNode.left = remove(data, curNode.left);
		else if(data.compareTo(curNode.data)>0)
			curNode.right = remove(data, curNode.right);
		return curNode;
	}
	
	/**
	 * Returns the max item in the given subtree
	 */
	public T findMax() {
		return findMax(this.root);
	}
	private T findMax(TreeNode<T> curNode) {
		//TODO: Implement this method
		if(curNode.right == null) 
			return curNode.data;
		return findMax(curNode.right);
	}
	
	private int actualHeight(TreeNode<T> curNode) { 
        return curNode != null ? curNode.height : 0;
    }
	private void updateHeight(TreeNode<T> node) {
        int leftHeight = actualHeight(node.left) + 1;
        int rightHeight = actualHeight(node.right) + 1;
        node.height = leftHeight > rightHeight ? leftHeight: rightHeight;
    }
}
