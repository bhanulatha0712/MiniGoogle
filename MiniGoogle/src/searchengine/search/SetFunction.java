/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine.search;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author bhanu
 */
public class SetFunction<T> implements SetADT<T>
{
    HashMap<String,Integer> hs;
    
    public SetFunction(HashMap<String,Integer> hm)
    {
        hs=hm;
    }
    
    public void add(T element)
    {
        /*if(!li.contains(element))
        {
            li.add(element);
        }*/
    }
    
    public T remove(T element)
    {
        
        return null;
        /*T temp=element;
        li.remove(element);
        return temp;*/
    }
    
    public SetADT<T> union(SetADT<T> set)
    {
        
        //HashMap<String,Integer> h1;
        HashMap<String,Integer> h2;
        hs=getmap();
        SetFunction<T> set1=(SetFunction<T>)set;
        h2=set1.getmap();
        Set r=h2.keySet();
        Iterator it=r.iterator();
        while(it.hasNext())
        {
            String as=(String)it.next();
            if(hs.get(as)==null)
            {
                hs.put(as,h2.get(as));
            }
            else if(hs.get(as)!=null)
            {
                int u=h2.get(as)+hs.get(as);
                
                hs.put(as,u);
            }
        }
            return this;
      
    }
    
    public SetADT<T> intersection(SetADT<T> set)
    {
        HashMap<String,Integer> h1=new HashMap<String,Integer>();
        HashMap<String,Integer> h2;
        hs=getmap();
        SetFunction<T> set1=(SetFunction<T>)set;
        h2=set1.getmap();
        Set r=h2.keySet();
        Iterator it=r.iterator();
        while(it.hasNext())
        {
            String as=(String)it.next();
            if(hs.get(as)==null)
            {
                
            }
            else if(hs.get(as)!=null)
            {
                int u=Math.min(h2.get(as),hs.get(as));
                u=2*u;
                h1.put(as,u);
            }
        }
        setmap(h1);    
        return this;
 
    }

    
    public SetADT<T> difference(SetADT<T> set)
    {
        //HashMap<String,Integer> h1=new HashMap<String,Integer>();
        HashMap<String,Integer> h2;
        hs=getmap();
        SetFunction<T> set1=(SetFunction<T>)set;
        h2=set1.getmap();
        Set r=h2.keySet();
        Iterator it=r.iterator();
        while(it.hasNext())
        {
            String as=(String)it.next();
            if(hs.get(as)==null)
            {
                
            }
            else if(hs.get(as)!=null)
            {
               hs.remove(as);
            }
        }
           
        return this;
 
    }
    
    public boolean contains(T target)
    {
        return false;
    }
 
  /**  Returns true if this set and the parameter contain exactly
       the same elements */
  public boolean equals(SetADT<T> set)
  {
      return false;
  }
 
  /**  Returns true if this set contains no elements */
  public boolean isEmpty()
  {
      return false;
  }
 
  /**  Returns the number of elements in this set */
  public int size()
  {
      return 0;
  }
  
 
  /**  Returns an iterator for the elements in this set */
  public Iterator<T> iterator()
  {
      return null;
  }
 
  /**  Returns a string representation of this set */
  public String toString()
  {
      return null;
  }

    
    public void setmap(HashMap<String,Integer> dup)
    {
        hs=dup;
    }
    
    public HashMap< String,Integer> getmap()
    {
        return hs;
    }
}
