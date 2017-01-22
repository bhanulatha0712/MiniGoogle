/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine.dictionary;
import java.io.*;
import java.util.*;

/**
 *
 * @author Administrator
 */
public class HashDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>,Serializable
{

	Hashtable<K,V> hs;
        
        public HashDictionary()                         // Constructor to initialize hashtable
        {
            hs=new Hashtable<K,V>();
        }
    
    
         @Override
	public K[] getKeys() 
         {
		// TODO Auto-generated method stub
             
                K[] a;
                Enumeration<K> e=hs.keys();             // an enumeration object to collect keys
                a=(K[])(new String[hs.size()]);
                
                
                for(int i=0;i<hs.size();i++)
                {
                    a[i]=e.nextElement();               // taking keys in an array
                }
		return (K[])a;                          // Returning array
	}

	@Override
	public V getValue(K str) {
		// TODO Auto-generated method stub
                
                return hs.get(str);
		//return null;
	}

	@Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
            
            hs.put(key, value);
		
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
            hs.remove(key);
		
	}

}
