package tree;

/**
 * Self-balancing AVL Tree
 * 
 * @author Mark Floryan
 *
 * @param <T>
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    @Override
    public void insert(T data) {
        this.root = insert(data, this.root);
    }

    private int actualHeight(TreeNode<T> curNode) { 
        return curNode != null ? curNode.height : 0;
    }

    protected TreeNode<T> insert(T data, TreeNode<T> curNode) {
        curNode = super.insert(data, curNode);
        updateHeight(curNode);
        return balance(curNode);
    }

    private void updateHeight(TreeNode<T> node) {
        int leftHeight = actualHeight(node.left) + 1;
        int rightHeight = actualHeight(node.right) + 1;
        node.height = leftHeight > rightHeight ? leftHeight: rightHeight;
    }

    @Override
    public void remove(T data) {
        this.root = remove(data, this.root);
    }

    protected TreeNode<T> remove(T data, TreeNode<T> curNode) {
        curNode = super.remove(data, curNode);
        if (curNode == null) {
            return null;
        }
        updateHeight(curNode);
        return balance(curNode);
    }

    /**
     * Balances the given node. Assumes it is the lowest unbalanced node if
     * unbalanced
     * 
     * @param node
     * @return
     */
    private TreeNode<T> balance(TreeNode<T> curNode) {
        int balance = balanceFactor(curNode);
        if (balance < -1) {
            if (balanceFactor(curNode.left) <= 0) { 
                curNode = rotateRight(curNode);
            } else { 
                curNode.left = rotateLeft(curNode.left);
                curNode = rotateRight(curNode);
            }
        }
        if (balance > 1) {
            if (balanceFactor(curNode.right) >= 0) {
                curNode = rotateLeft(curNode);
            } else { 
                curNode.right = rotateRight(curNode.right);
                curNode = rotateLeft(curNode);
            }
        }
        return curNode;
    }

    private TreeNode<T> rotateRight(TreeNode<T> curNode) {
        TreeNode<T> leftChild = curNode.left;
        curNode.left = leftChild.right;
        leftChild.right = curNode;

        updateHeight(curNode);
        updateHeight(leftChild);
        return leftChild;
    }

    private TreeNode<T> rotateLeft(TreeNode<T> curNode) {
        TreeNode<T> rightChild = curNode.right;
        curNode.right = rightChild.left;
        rightChild.left = curNode;

        updateHeight(curNode);
        updateHeight(rightChild);
        return rightChild;
    }

    private int balanceFactor(TreeNode<T> curNode) {
        return actualHeight(curNode.right) - actualHeight(curNode.left);
    }

}