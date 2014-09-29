/**
* Trinary Tree is a tree, in which there are three child nodes for each parent
*  -- with the left node being values less than the parent, the right node 
*  values greater than the parent, and the middle nodes values equal to the parent.
*  
* @author Xia Wu
* @Time 9/28/2014
*
*/

public class TrinaryTree{
	TreeNode root;
	TrinaryTree(){
		this.root = null;
	}

	/**
	 * Insert a node with value
	 * @param nval value of the node to add
	 */
	void insert(int val){
		TreeNode nnode = new TreeNode(val);
		if(this.root==null)
			this.root = nnode;
		else{
			TreeNode cur = this.root, parent = null;
			while(cur!=null){
				parent = cur;
				if(cur.val < val)
					cur = cur.right;
				else if(cur.val > val)
					cur = cur.left;
				else
					cur = cur.mid;
			}
			if(parent.val < val)
				parent.right = nnode;
			else if(parent.val > val)
				parent.left = nnode;
			else
				parent.mid = nnode;
		}
	}

	/**
	 * Delete a node having the target value
	 * @param nval the value of the node to delete
	 * @return true if succeed, false if fail
	 */
	boolean delete(int tval){
		if(this.root==null)
			return false;
		TreeNode cur = this.root, parent = null;
		//move cur to the node to be deleted, parent is cur's parent node
		while(cur!=null){
			if(cur.val > tval){
				parent = cur;
				cur = cur.left;
			}
			else if(cur.val < tval){
				parent = cur;
				cur = cur.right;
			}
			else
				break;
		}
		if(cur==null)
			return false;
		
		if(cur.mid!=null){
			//if cur has same value children, delete the most bottom one
			while(cur!=null){
				if(cur.mid==null){
					parent.mid=null;
					return true;
				}
				parent = cur;
				cur = cur.mid;
			}
		}

		if(cur==root)
			root = cur.right!=null ? cur.right : cur.left;
		else{
			if(parent.left==cur)
				parent.left = cur.right!=null ? cur.right : cur.left;
			else
				parent.right = cur.right!=null ? cur.right : cur.left;
		}
		if(cur.right!=null)
			leftMost(cur.right).left = cur.left;
		return true;
	}
	
	/**
	 * Find the most left node
	 * @param t the node starting from
	 * @return true if succeed, false if fail
	 */
	TreeNode leftMost(TreeNode t){
		if(t==null || t.left==null)
			return t;
		else
			return leftMost(t.left);
	}
}

class TreeNode {
	int val;
	TreeNode left, mid, right;
	TreeNode(int i){
		this.val = i;
		this.left = null;
		this.mid = null;
		this.right = null;
	}
}
