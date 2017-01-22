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
 * In the binary tree the nodes are inserted by comparision like 
 * node with key less than parent key is inserted at left of parent
 * and node with key more than parent key is inserted at right of parent
 * The deletion is done by checking three nodes
 * If the node is leaf node then deletion is done by making it null
 * If it has one child then it is replaced by its child
 * If it has two nodes then it is replaced by its successor
 */

class Node<String,V> 
{
    String key;
    V value;
    Node<String,V> parent;
    Node<String,V> left;
    Node<String,V> right;
    
    public Node(String ke,V val)
    {
        key=ke;
        value=val;
    }
    
    public void displayNode()
    {
        //System.out.print("  " + key + "  " + value );
    }
}


public class BSTDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>,Serializable
{

	Node<String,V> root;
        String a[];
        int size=0;
        int i=0;
        
        public BSTDictionary()              //Default Constructor
        {
            root=null;
        }
    
        public boolean isEmpty()            // Checking for empty condition
        {
            return root==null;
        }
        
        
        public String[] traverse(Node<String,V> locroot)        // Traversing recursively that is inorder traversal
        {
            if(locroot!=null)                                   
            {
                traverse(locroot.left);                         // First the left nodes
                a[i++]=locroot.key;                             // Root node
                traverse(locroot.right);                        // Right nodes
            }
                return a;
        }
        
        
        
        @Override
	public K[] getKeys() 
        {
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
            Node<String,V> cur=root;                            // Traversing to get value
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

	public Node search(String ke)                   // Searching to get parent to insert a node
        {
            Node<String,V> cur=root;
            Node<String,V> temp=null;
            while(cur!=null)
            {
                if((ke.compareTo(cur.key))>0)
                {
                    temp=cur;
                    cur=cur.right;
                }
                else if((ke.compareTo(cur.key))<0)
                {
                    temp=cur;
                    cur=cur.left;
                }
                else
                {
                    return null;
                }
            }
                return temp;                            // Returning the parent
            
        }
        
        @Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
            
            root=insert((String)key,root,value);
		
	}
        
        public Node<String,V> insert(String ke,Node<String,V> t,V val)
        {
            if(t==null)
            {
                size++;
                return new Node<String,V>(ke,val);
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
                System.out.println(" The value already exists in the tree ");
            }
                return t;
        }

	public Node<String,V> find(String ke)                       // To find the node containing the key
        {
            Node<String,V> cur=root;
            
            while(cur!=null)
            {
                if((ke.compareTo(cur.key))>0)
                {
                    
                    cur=cur.right;
                }
                else if((ke.compareTo(cur.key))<0)
                {
                    
                    cur=cur.left;
                }
                else
                {
                    return cur;
                }
            }
                
		return null;
        }
        
        public Node<String,V> successor(Node<String,V> pre)         // To get successor of key for deletion
        {
            Node<String,V> cur=pre;
            if(cur.right!=null)
            {
                cur=cur.right;
            
            while(cur.left!=null)
            {
                cur=cur.left;
            }
                return cur;
            }
            else
            {
                while(cur.parent!=null && cur.parent.right==cur)
                {
                    cur=cur.parent;
                }
                    return cur.parent;
            }
            
        }
        
        
        @Override
	public void remove(K key) {
		// TODO Auto-generated method stub
            Node<String,V> cur=remove((String)key,root);
             if(cur!=null)
             {
                 size--;
             }
		
	}
        
        public Node<String,V> remove(String ke,Node<String,V> t)
        {
            if(t==null)
            {
                System.out.println(" The required value doesnot exist in the bucket ");
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
                return t;
        }
        
        public Node<String,V> findMin(Node<String,V> t)
        {
            Node<String,V> cur=t;
            while(cur.left!=null)
            {
                cur=cur.left;
            }
            return cur;
        }

}
