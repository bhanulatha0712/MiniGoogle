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


/*class ListNode<K extends Comparable<K>, V>{
	K key;
	V value;
	ListNode<K, V> next;
}*/

class DicLink<K, V>
{
    K key;
    V value;
    DicLink<K, V> next;
    
    public DicLink(K k,V v)
    {
        key=k;
        value=v;
    }
    
    public void dispalylink()
    {
        System.out.println(" "+ key + "     " + value);
    }
}





public class ListDictionary<K extends Comparable<K>, V> implements DictionaryInterface<K,V>,Serializable
{
    DicLink<K,V> first;
    
    public ListDictionary()
    {
        first=null;
    }
    
    public boolean isEmpty()
    {
        return first==null;
    }
    
    
    public void insert(K ke,V val)
    {
        DicLink<K,V> newlink=new DicLink<K,V>(ke,val);
        if(isEmpty())
        {
            first=newlink;
        }
        
        
        else
        {
            DicLink<K,V> current=first;
            int check=1;
            while(current!=null)
            {
                if(current.key.equals(ke))
                {
                    check=0;
                    break;
                }
                else
                {
                    check=1;
                }
                current=current.next;
            }
            if(check==0)
            {
                System.out.println(" Overwriting value ");
                current.value=val;
            }
            else
            {
            newlink.next=first;
            first=newlink;
            }
        }
    }
    
    public V getValue(K str)
    {
        DicLink<K,V> current=first;
        
        while((!(current.key.equals(str))))
        {
            if(current.next==null)
            {
                return null;
            }
            current=current.next;
        }
        
        if(current.key.equals(str))
        {
            return current.value;
        }
            return null;
    }
    
    public void remove(K key)
    {
        DicLink<K,V> current=first;
        DicLink<K,V> previous=first;
        
        while(!(current.key.equals(key)))
        {
            if(current.next==null)
            {
                System.out.println(" Invalid key is entered for deletion ");
                return;
            }
            System.out.println(" key is : "+current.key + " with "+ key);
            previous=current;
            current=current.next;
            System.out.println(" checked ");
        }
        
        if(current.key.equals(key))
        {
            if(current==first)
            {
                current=current.next;
            }
            else
            {
                previous.next=current.next;
            }
        }
    }
    
    public int getnoofkeys()
    {
        DicLink<K,V> current=first;
        int i=0;
        while(current!=null)
        {
            current=current.next;
            i++;
        }
        return i;
    }
    
    
    
    public K[] getKeys()
    {
        //K[] a;
        DicLink<K,V> current=first;
        int j;
        j=getnoofkeys();
        //a=new K[10];
        //a=(K[])(new Comparable[j]);
        String[] a;
        a=new String[j];                                        //Doubt
        if(j==0)
        {
            return null;
        }
        else
        {
        for(int t=0;t<j;t++)
        {
           a[t]=(String)current.key;
           System.out.println(" key is : "+a[t]);
            //a[k]=(K)a[k];
           current=current.next;
        }
        }
        return (K[])a;
    }
}
