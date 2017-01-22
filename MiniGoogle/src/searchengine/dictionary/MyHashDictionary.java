/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine.dictionary;

/**
 *
 * @author Administrator
 */
import java.io.*;
import java.util.*;

class codegen<K>                                            //Class to generate code for each key
{
    private int code=0;
    private int index;
    public String word;
    
    codegen(K wo)                                           // Code generation for a key
    {
        word=(String)wo;
        for(int i=0;i<word.length();i++)
        {
            code=(code*3)+((int)word.charAt(i));
        }
        
    }
    
    public int getindex()                                   // To get index of a bucket in which key is palced
    {
        index=(code%25);
        return index;
    }
    
}




class HLink<K,V>                                            // Link for each key value pair
{
    K key;
    V value;
    HLink<K,V> next;
    
    public HLink(K ke,V val)
    {
        key=ke;
        value=val;
    }
}

class BucketList<K extends Comparable<K>, V>                    // Array of linked lists
{
    HLink<K,V> first;
    public BucketList()                     
    {
    
        first=null;
    }
    
    public boolean isEmpty()
    {
        return first==null;
    }
    
    public void bukinsert(K ke,V val)                           // Inserting in bucket as a linked list
    {
        HLink<K,V> newlink=new HLink<K,V>(ke,val);
        
        if(isEmpty())
        {
            first=newlink;
        }
        else
        {
            newlink.next=first;
            first=newlink;
        }
    }
    
    public V getval(K ke)                                       // Getting value for a key by going to that particular bucket
    {
        HLink<K,V> current=first;
        if(isEmpty())
        {
            return null;
        }
        else
        {
            while(!(current.key.equals(ke)))
            {
                if(current.next==null)
                {
                    return null;
                }
                current=current.next;
            }
            if(current.key.equals(ke))
            {
                return current.value;
            }
                return null;
        }
    }
    
    public boolean contains(K ke,V val)                     // Checks if key already exists in that bucket
    {
        HLink<K,V> current=first;
        if(!isEmpty())
        {
        while(!(current.key.equals(ke)))
        {
                if(current.next==null)
                {
                    return false;
                }
                
                current=current.next;
        }
        if(current.key.equals(ke))
        {
            current.value=val;
            return true;
        }
        }
            return false;
    }
    
    
    
    public V removeval(K ke)                        // Removing that particular key link in the bucket
    {
        HLink<K,V> current=first;
        HLink<K,V> previous=first;
        if(isEmpty())
        {
            return null;
        }
        else
        {
            while(!(current.key.equals(ke)))
            {
                if(current.next==null)
                {
                    return null;
                }
                previous=current;
                current=current.next;
            }
            if(current==first)
            {
                HLink<K,V> temp=current;
                first=first.next;
                return temp.value;
            }
            else
            {
                HLink<K,V> temp=current;
                previous.next=current.next;
                return temp.value;
            }
        }
    }
}


public class MyHashDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>,Serializable
{

	BucketList[] buck;                          // Creating array of buckets
        codegen co;                                 // Instantiating codgen class
        int size=0;
    
        public MyHashDictionary()
        {
            buck=new BucketList[25];                // Array of buckets
            for(int i=0;i<25;i++)
            {
                buck[i]=new BucketList();
            }
        }
        
        @Override
	public K[] getKeys() 
        {
		
                // TODO Auto-generated method stub
            K[] a;
            a=(K[])(new String[size]);
            int k=0;
            for(int i=0;i<25;i++)
            {                                                       // Collecting the keys from buckets
                if(!(buck[i].isEmpty()))
                {
                    HLink<K,V> current=buck[i].first;   
                    while(current!=null)
                    {
                        a[k]=current.key;
                        k++;
                        current=current.next;
                    }
                }
                
            }
                return a;
            
	}

	@Override
	public V getValue(K str)                                    // Getting value for a key by just pointing to a bucket
        {
		// TODO Auto-generated method stub
            int ind;
            co=new codegen(str);
            ind=co.getindex();
            return (V)buck[ind].getval(str);
            
	}

	@Override
	public void insert(K key, V value)                          // Inserting a link in a bucket
        {
            
                int ind;
                co=new codegen(key);
                ind=co.getindex();
                boolean b=buck[ind].contains(key, value);
                if(b==false)
                {
                buck[ind].bukinsert(key, value);
                size++;
                }
               
                    
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(K key) {                                     // Removing a key value pair
		// TODO Auto-generated method stub
		int ind;
                co=new codegen(key);
                ind=co.getindex();
                V val=(V)buck[ind].removeval(key);
                if(val==null)
                {
                    System.out.println(" Key doesnot exist to remove ");
                }
                else
                {
                    size--;
                }
	}

}
