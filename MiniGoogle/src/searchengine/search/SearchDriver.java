/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender K on 12-10-2009
 */ 


package searchengine.search;


import java.util.*;
import java.io.*;

import searchengine.dictionary.ObjectIterator;
import searchengine.indexer.Indexer;


/**
 * The user interface for the index structure.
 *
 * This class provides a main program that allows users to search a web
 * site for keywords.  It essentially uses the index structure generated
 * by WebIndex or ListWebIndex, depending on parameters, to do this.
 *
 * To run this, type the following:
 *
 *    % java SearchDriver indexfile list|custom keyword1 [keyword2] [keyword3] ...
 *
 * where indexfile is a file containing a saved index and list or custom indicates index structure.
 *
 */
class Printing
{
    String ur;
    int freq;
    public Printing(String st,int num)
    {
        freq=num;
        ur=st;
    }
}


public class SearchDriver
{
    public static void main(String [] args)
    {
        Vector<String> v=new Vector<String>();
	
	if(args.length<3)
	    System.out.println("Usage: java SearchDriver indexfile list|hash keyword1 [keyword2] [keyword3] [...]");
	else
	    {
		Indexer w = null;
		
		// Take care to use the right usage of the Index structure
		// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
		// list - Dictionary Structure based on Linked List 
		// myhash - Dictionary Structure based on a Hashtable implemented by the students
		// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
		// avl - Dictionary Structure based on AVL Tree implemented by the students
		if(args[1].equalsIgnoreCase("list") || args[1].equals("hash") || args[1].equals("myhash") || args[1].equals("bst") 
				|| args[1].equals("avl")){
		    w = new Indexer(args[1]);
		}
		else
		{
			System.out.println("Invalid Indexer mode \n");
		}
		
		try{
		    FileInputStream indexSource=new FileInputStream(args[0]);
		    w.restore(indexSource);
		}
		catch(IOException e){
		    System.out.println(e.toString());
		}
		
		for(int i=2;i<args.length;i++)
		    v.addElement(args[i]);
		
		ObjectIterator<?> i= w.retrievePages(new ObjectIterator<String>(v));
		
		if(i!=null)
		{
			////////////////////////////////////////////////////////////////////
		    //  Write your Code here as part of Sorting based on Rank Assignment
		    //  
		    ///////////////////////////////////////////////////////////////////
			HashMap<String,Integer> hsh=null;
                        Printing pr;
                        while(i.hasNext())
                        {
                            int num;
                            hsh=(HashMap<String, Integer>) i.next();
                            Vector<Printing> ve=new Vector<Printing>();
                            //String[] urls=null;
                            //urls=hsh.keySet().toArray(urls);
                            /*for(int j=0;j<urls.length;j++)
                            {
                                num=hsh.get(urls[j]);
                                pr=new Printing(urls[j],num);
                                ve.add(pr);                             
                            }*/
                            Set se=hsh.keySet();
                            Iterator itr=se.iterator();
                            while(itr.hasNext())
                            {
                                
                                String s=(String)itr.next();
                                num=hsh.get(s);
                                pr=new Printing(s,num);
                                ve.add(pr);
                            }
                            
                            //System.out.println(" THE URLS IN VECTOR ARE : "+ve.get(0).ur);
                            for(int j=0;j<ve.size();j++)
                            {
                                for(int t=0;t<(ve.size()-j-1);t++)
                                {
                                    
                                    
                                    if(((ve.get(t).freq)/(float)depth(ve.get(t).ur))<((ve.get(t+1).freq)/(float)depth(ve.get(t+1).ur)))
                                    {
                                        Printing check=ve.get(t);
                                        
                                        ve.set(t,ve.get(t+1));
                                        //ve.remove(t+1);
                                        ve.set(t+1,check);
                                        
                                    }
                                }
                            }
                            
                            System.out.println(" ****** THE URLS FOR THE WORD ARE ******** ");
                            
                            for(int h=0;h<ve.size();h++)
                            {
                                System.out.println(" URL : "+ve.get(h).ur);
                            }
                            
                        }
                    
                    
                        System.out.println("Search complete.");
			System.out.println("---------------\n");
		}
		else
		{
			System.out.println("Search complete.  0  hits found.");
		}
	    }
        
        
    }
    
        public static int depth(String str)
        {
            char[] c;
            int count=0;
            for(int p=0;p<str.length();p++)
            {
                if(str.charAt(p)=='/')
                {
                    count++;
                }
            }
                return count-1;
        }
};


