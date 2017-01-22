/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */ 

package searchengine.indexer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Vector;
import java.io.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import searchengine.dictionary.AVLDictionary;
import searchengine.dictionary.BSTDictionary;
import searchengine.dictionary.DictionaryInterface;
import searchengine.dictionary.HashDictionary;
import searchengine.dictionary.ListDictionary;
import searchengine.dictionary.MyHashDictionary;
import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageWord;
import searchengine.search.SetADT;
import searchengine.search.SetFunction;


class Document
{
    URL ut;
    //HashMap<String,Integer> ha;
    
    public Document(String st) throws Exception
    {
        ut=new URL(st);
        //ha=new HashMap<String,Integer>();
    }
}




/**
 * Web-indexing objects.  This class implements the Indexer interface
 * using a list-based index structure.

A Hash Map based implementation of Indexing 

 */
public class Indexer implements IndexerInterface
{
	/** The constructor for ListWebIndex.
	 */

	// Index Structure 
	DictionaryInterface<String,HashMap<String,Integer>> index;

	// This is for calculating the term frequency
	HashMap<String,Integer> wordFrequency;

	public Indexer(String mode)
	{
		// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
		// list - Dictionary Structure based on Linked List 
		// myhash - Dictionary Structure based on a Hashtable implemented by the students
		// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
		// avl - Dictionary Structure based on AVL Tree implemented by the students

		if (mode.equals("hash")) 
			index = new HashDictionary<String,HashMap<String,Integer>>();
		else if(mode.equals("list"))
			index = new ListDictionary<String,HashMap<String,Integer>>();
		else if(mode.equals("myhash"))
			index = new MyHashDictionary<String,HashMap<String,Integer>>();
		else if(mode.equals("bst"))
			index = new BSTDictionary<String,HashMap<String,Integer>>();
		else if(mode.equals("avl"))
			index = new AVLDictionary<String,HashMap<String,Integer>>();
	}

	/** Add the given web page to the index.
	 *
	 * @param url The web page to add to the index
	 * @param keywords The keywords that are in the web page
	 * @param links The hyperlinks that are in the web page
	 */
	public void addPage(URL url, ObjectIterator<?> keywords)
	{
	    Vector<PageElementInterface> v;
            
            v=(Vector<PageElementInterface>)keywords.returnVec();
           
            for(int i=0;i<v.size();i++)
            {
                String ch=v.get(i).toString();
                
                if(index.getValue(ch)==null)
                {
                    HashMap<String,Integer> h=new HashMap<String,Integer>();
                    //System.out.println(" Inserted newly the word "+ch+ " in url ");
                    h.put(url.toString(),1);
                    index.insert(ch,h);
                }
                
                else if(index.getValue(ch)!=null)
                {
                    
                    wordFrequency=(HashMap<String,Integer>)index.getValue(ch);
                    if(wordFrequency.get(url.toString())!=null)
                    {
                        int y;
                        y=(Integer)wordFrequency.get(url.toString());
                        y++;
                        //System.out.println(" till now the word  "+ ch + " are "+y);
                        wordFrequency.put(url.toString(), y);                        
                    }
                    else if(wordFrequency.get(url.toString())==null)
                    {
                        wordFrequency.put(url.toString(),1);
                    }
                    
                }
                
            }
            ////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
	}

	/** Produce a printable representation of the index.
	 *
	 * @return a String representation of the index structure
	 */
	public String toString()
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		return "You dont need to implement it\n";
	}

	/** Retrieve all of the web pages that contain the given keyword.
	 *
	 * @param keyword The keyword to search on
	 * @return An iterator of the web pages that match.
	 */
	public ObjectIterator<?> retrievePages(PageWord keyword)
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		return new ObjectIterator<PageElementInterface>(new Vector<PageElementInterface>());
	}

	/** Retrieve all of the web pages that contain any of the given keywords.
	 *	
	 * @param keywords The keywords to search on
	 * @return An iterator of the web pages that match.
	 * 
	 * Calculating the Intersection of the pages here itself
	 **/
	public ObjectIterator<?> retrievePages(ObjectIterator<?> keywords)
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
            int count=0;
            Vector<HashMap<String,Integer>> col=new Vector<HashMap<String,Integer>>();
            System.out.println(" Select an action : ");
            System.out.println(" 1. The URLS of Individual words ");
            System.out.println(" 2. The set of Intersected URLs");
            System.out.println(" 3. The Union of URLs");
            System.out.println(" 4. The difference of URLs");
            Scanner in=new Scanner(System.in);
            int choice;
            choice=in.nextInt();
            SetADT<String> se=null;
            switch(choice)
            {
            
            case 1:   
                while(keywords.hasNext())
            {
                String ch=(String)keywords.next();
                wordFrequency=index.getValue(ch);
                System.out.println(" word is : "+ch);
                if(wordFrequency!=null)
                {
                   count++;
                   col.add(wordFrequency);
                   Set fr=wordFrequency.keySet();
                   Iterator itr=fr.iterator();
                   while(itr.hasNext())
                   {
                       System.out.println(" url is : "+itr.next());
                   }
                }
                
              
            }
            ObjectIterator<HashMap<String,Integer>> obj=new ObjectIterator<HashMap<String,Integer>>(col);
            if(count>0)
            {
            return obj;
            }
            else
            {
                return null;
            }
                //break;
                
            case 2 : 
            case 3 :
            case 4 :
                        if(keywords.hasNext())
                        {
                           String ch=(String)keywords.next();
                           wordFrequency=index.getValue(ch);
                           se=new SetFunction<String>(wordFrequency);
                        }
                            SetADT<String> se1=null;
                        while(keywords.hasNext())
                        {
                            String ch=(String)keywords.next();
                            wordFrequency=index.getValue(ch);
                            se1=new SetFunction<String>(wordFrequency);
                            if(choice==2)
                            {
                                se=(SetFunction<String>)se.intersection(se1);
                            }
                            else if(choice==3)
                            {
                                se=(SetFunction<String>)se.union(se1);
                            }
                            else if(choice==4)
                            {
                                se=(SetFunction<String>)se.difference(se1);
                            }
                        }
                        
                        SetFunction<String> sr=(SetFunction<String>)se;
                        wordFrequency=sr.getmap();
                        col.add(wordFrequency);
                        ObjectIterator<HashMap<String,Integer>> obj1=new ObjectIterator<HashMap<String,Integer>>(col);
                        if(col!=null)
                        {
                            return obj1;
                        }
                        else
                        {
                            return null;
                        }
                        
            default : System.out.println(" Invalid choice ");
                        break;
           
            //return new ObjectIterator<PageElementInterface>(new Vector<PageElementInterface>());
            }
                return null;
       }
        
        
        public ObjectIterator<?> retrievePages(ObjectIterator<?> keywords,int choice)
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
            int count=0;
            Vector<HashMap<String,Integer>> col=new Vector<HashMap<String,Integer>>();
            /*System.out.println(" Select an action : ");
            System.out.println(" 1. The URLS of Individual words ");
            System.out.println(" 2. The set of Intersected URLs");
            System.out.println(" 3. The Union of URLs");
            System.out.println(" 4. The difference of URLs");
            Scanner in=new Scanner(System.in);
            int choice;
            choice=in.nextInt();*/
            SetADT<String> se=null;
            switch(choice)
            {
            
            case 1:   
                while(keywords.hasNext())
            {
                String ch=(String)keywords.next();
                wordFrequency=index.getValue(ch);
                System.out.println(" word is : "+ch);
                if(wordFrequency!=null)
                {
                   count++;
                   col.add(wordFrequency);
                   Set fr=wordFrequency.keySet();
                   Iterator itr=fr.iterator();
                   while(itr.hasNext())
                   {
                       System.out.println(" url is : "+itr.next());
                   }
                }
                
              
            }
            ObjectIterator<HashMap<String,Integer>> obj=new ObjectIterator<HashMap<String,Integer>>(col);
            if(count>0)
            {
            return obj;
            }
            else
            {
                return null;
            }
                //break;
                
            case 2 : 
            case 3 :
            case 4 :
                        if(keywords.hasNext())
                        {
                           String ch=(String)keywords.next();
                           wordFrequency=index.getValue(ch);
                           se=new SetFunction<String>(wordFrequency);
                        }
                            SetADT<String> se1=null;
                        while(keywords.hasNext())
                        {
                            String ch=(String)keywords.next();
                            wordFrequency=index.getValue(ch);
                            se1=new SetFunction<String>(wordFrequency);
                            if(choice==2)
                            {
                                se=(SetFunction<String>)se.intersection(se1);
                            }
                            else if(choice==3)
                            {
                                se=(SetFunction<String>)se.union(se1);
                            }
                            else if(choice==4)
                            {
                                se=(SetFunction<String>)se.difference(se1);
                            }
                        }
                        
                        SetFunction<String> sr=(SetFunction<String>)se;
                        wordFrequency=sr.getmap();
                        col.add(wordFrequency);
                        ObjectIterator<HashMap<String,Integer>> obj1=new ObjectIterator<HashMap<String,Integer>>(col);
                        if(col!=null)
                        {
                            return obj1;
                        }
                        else
                        {
                            return null;
                        }
                        
            default : System.out.println(" Invalid choice ");
                        break;
           
            //return new ObjectIterator<PageElementInterface>(new Vector<PageElementInterface>());
            }
                return null;
            }
        

        

	/** Save the index to a file.
	 *
	 * @param stream The stream to write the index
	 */
	public void save(FileOutputStream stream) throws IOException
	{
            
            ObjectOutputStream bu=new ObjectOutputStream(stream);
            bu.writeObject(index);
            System.out.println(" index is saved ");
           
            
            ////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
	}

	/** Restore the index from a file.
	 *
	 * @param stream The stream to read the index
	 */
	public void restore(FileInputStream stream) throws IOException
	{
            ObjectInputStream oi=null;
            oi=new ObjectInputStream(stream);
            try
            {
            //DictionaryInterface<String,HashMap<String,Integer>> check;
            index=(HashDictionary<String,HashMap<String,Integer>>)oi.readObject();
            //index=check;
            String a[];
            a=index.getKeys();
           /* System.out.println(" keys are : ");
            for(int i=0;i<a.length;i++)
            {
                System.out.println("Word is :"+a[i]);
                wordFrequency=index.getValue(a[i]);
                Set sd=wordFrequency.keySet();
                Iterator itr=sd.iterator();
                System.out.println("Its URLs are : ");
                while(itr.hasNext())
                {
                    String y=(String)itr.next();
                    System.out.println(" url : "+y + "freq : "+wordFrequency.get(y));
                }
            }*/
            }
            catch(Exception e)
            {
                System.out.println(" Class not found exception ");
            }
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
	}

	/* Remove Page method not implemented right now
	 * @see searchengine.indexer#removePage(java.net.URL)
	 */
	public void removePage(URL url) {
	}
};
