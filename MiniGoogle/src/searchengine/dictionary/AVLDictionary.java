/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine.dictionary;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */

/*
 * In the AVL tree the tree should be balanced
 * For this the height difference between left and right subtree should be atmost 1
 * when imbalance occurs when node is inserted in left child of left subtree or 
 * right child of right subtree then single rotation should be done
 * when imbalance occurs when node is inserted in right child of left subtree or 
 * left child of right subtree then double rotation should be done
 
 */

class ANode<String,V> 
{
    V value;
    String key;
    ANode<String,V> left;
    ANode<String,V> right;
    int height;
    
    public ANode(String ke,V val)
    {
        key=ke;
        value=val;
        left=null;
        right=null;
    }
    
    
}


public class AVLDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>,Serializable
{

	ANode<String,V> root;
        String a[];
        int size=0;
        int i=0;
    
        public AVLDictionary()
        {
            root=null;
        }
        
        public boolean isEmpty()
        {
            return root==null;
        }
        
        
        public String[] traverse(ANode<String,V> locroot)        // Traversing recursively that is inorder traversal
        {
            if(locroot!=null)                                   
            {
                traverse(locroot.left);                         // First the left nodes
                System.out.println(" keys are : "+locroot.key);
                a[i++]=locroot.key;                             // Root node
                traverse(locroot.right);                        // Right nodes
            }
                return a;
        }
        
        @Override
	public K[] getKeys() {
		// TODO Auto-generated method stub
                if(isEmpty())
                {
                    System.out.println(" Binary tree is empty ");   // Dispalying when binary tree is empty
                    return null;
                }
                else
                {
                    i=0;                                                // Initializing array index
                    a=new String[size];                                 // Allocating size to array
                    a=traverse(root);                                   // Traversing starting with root
                    return (K[])a;                                      // Returning array
                }
		
	}

	@Override
	public V getValue(K str) {
		// TODO Auto-generated method stub
            ANode<String,V> cur=root;                            // Traversing to get value
            String ch=(String)str;
            while(cur!=null)
            {
                if((ch.compareTo(cur.key))>0)
                {
                    
                    cur=cur.right;                              // Traversing right if key is big
                }
                else if((ch.compareTo(cur.key))<0)
                {
                    
                    cur=cur.left;                               // Traversing left if key is small
                }
                else
                {
                    return cur.value;                           // returning node
                }
            }
                
		return null;
		
	}

	
        
        public int height(ANode<String,V> t)
        {
            return t==null?-1:t.height;
        
        }
        
        
        @Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
            root=insert((String)key,root,value);
		
	}
        
        public ANode<String,V> insert(String ke,ANode<String,V> t,V val)
        {
            if(t==null)
            {
                size++;
                return new ANode<String,V>(ke,val);
            }
            int result=ke.compareTo(t.key);
            if(result<0)
            {
                t.left=insert(ke,t.left,val);
            }
            else if(result>0)
            {
                t.right=insert(ke,t.right,val);
            }
            else
            {
                System.out.println(" Key already exists ");
            }
        
            return balance(t);
        
        }
        
        public ANode<String,V> balance(ANode<String,V> t)
        {
            if(t==null)
            {
                return t;
            }
            if(height(t.left)-height(t.right)>1)
            {
                if(height(t.left.left)>=height(t.left.right))
                {
                    t=rotateWithLeftChild(t);
                }
                else
                {
                    t=doubleWithLeftChild(t);
                }
            }
            else if(height(t.right)-height(t.left)>1)
            {
                if(height(t.right.right)>=height(t.right.left))
                {
                    t=rotateWithRightChild(t);
                }
                else
                {
                    t=doubleWithRightChild(t);
                }
            }
            else
            {
                
            }
            t.height=Math.max(height(t.left), height(t.right))+1;
            return t;
        
        }
        
        
        
        public ANode<String,V> rotateWithLeftChild(ANode<String,V> k2)
        {
            ANode<String,V> k1=k2.left;
            k2.left=k1.right;
            k1.right=k2;
            k2.height=Math.max( height( k2.left ), height( k2.right ) ) + 1;
            k1.height=Math.max( height( k1.left ), k2.height ) + 1;
            System.out.println(" left rotated ");
            return k1;
        }
        
        public ANode<String,V> doubleWithLeftChild(ANode<String,V> k3)
        {
            k3.left=rotateWithRightChild(k3.left);
            System.out.println(" Double left rotated ");
            return rotateWithLeftChild(k3);
        }
        
        public ANode<String,V> rotateWithRightChild(ANode<String,V> k2)
        {
            ANode<String,V> k1=k2.right;
            k2.right = k1.left;
            k1.left = k2;
            k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
            k1.height = Math.max( height( k1.right ), k2.height ) + 1;
            System.out.println(" right rotated ");
            return k1;
        }
        
        public ANode<String,V> doubleWithRightChild(ANode<String,V> k3)
        {
            k3.right=rotateWithLeftChild(k3.right);
            System.out.println(" Double right rotated ");
            return rotateWithRightChild(k3);
        }
        
        
        

	@Override
	public void remove(K key) 
        {
		// TODO Auto-generated method stub
		ANode<String,V> cur=remove((String)key,root);
                if(cur!=null)
                {
                    size--;
                }
	}
        
        public ANode<String,V> remove(String ke,ANode<String,V> t)
        {
            if(t==null)
            {
                System.out.println(" The key doesnot exist in the tree ");
                return t;
            }
            int result=ke.compareTo(t.key);
            if(result<0)
            {
                t.left=remove(ke,t.left);
            }
            else if(result>0)
            {
                t.right=remove(ke,t.right);
            }
            else if(t.left!=null && t.right!=null)
            {
                t.key=findMin(t.right).key;
                t.right=remove(t.key,t.right);
            }
            else
            {
                t=(t.left!=null)?t.left:t.right;
            }
            return balance(t);
        }
        
        public ANode<String,V> findMin(ANode<String,V> t)
        {
            ANode<String,V> cur=t;
            while(cur.left!=null)
            {
                cur=cur.left;
            }
            return cur;
        }

}