/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Created by Mahender on 12-10-2009
 */
package searchengine.spider;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Vector;
import searchengine.dictionary.Comp;
import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageWord;

import searchengine.indexer.Indexer;
import searchengine.parser.PageLexer;
import searchengine.url.URLTextReader;

/** Web-crawling objects.  Instances of this class will crawl a given
 *  web site in Priority-first order.
 */
public class PriorityBasedSpider implements SpiderInterface {

	/** Create a new web spider.
	@param u The URL of the web site to crawl.
	@param i The initial web index object to extend.
	 */

	private Indexer i = null;
	private URL u; 
        

	public PriorityBasedSpider (URL u, Indexer i) 
        {
		this.u = u;
		this.i = i;
                
	}

	/** Crawl the web, up to a certain number of web pages.
	@param limit The maximum number of pages to crawl.
	 */
	public Indexer crawl (int limit) 
        {
            PriorityQueue<URL> pq = new PriorityQueue<URL>(1,new Comp());
            int il=1;   
            URLTextReader in = new URLTextReader(u);
            LinkedList<String> list=new LinkedList<String>();
            ObjectIterator<PageElementInterface> obj=null;
            Vector<PageElementInterface> vec=null;
            list.add(u.toString());
        try 
        {
            vec=new Vector<PageElementInterface>();
            
            PageLexer<PageElementInterface> elts = new PageLexer<PageElementInterface>(in, u);
                while (elts.hasNext()) 
                {
                    PageElementInterface elt = (PageElementInterface)elts.next();
                    if(elt instanceof PageHref && (!list.contains(elt.toString())))
                    {
                        pq.add(new URL(elt.toString()));
                        list.add(elt.toString());
                        //il++;
                    }
                    else if(elt instanceof PageWord)
                    {
                        vec.add(elt);
                    }
                }
             
             obj=new ObjectIterator<PageElementInterface>(vec);
             i.addPage(u, obj);
                
                while(!pq.isEmpty() && il<=limit)
                {
                    vec=new Vector<PageElementInterface>();
                    URL ch=new URL(pq.poll().toString());
                    System.out.println("web: "+ch.toString());
                    il++;
                    
                    //URL r=new URL(ch);
                    URLTextReader on = new URLTextReader(ch);
                    //System.out.println(" Level 111111111");
                    PageLexer<PageElementInterface> elch=null;
                    try
                    {
                    elch = new PageLexer<PageElementInterface>(on, ch);
                    }
                    catch(IOException ioe)
                    {
                        continue;
                    }
                    //System.out.println(" Level 20000000000");
                    while(elch.hasNext())
                    {
                        PageElementInterface elc = (PageElementInterface)elch.next();
                        if(elc instanceof PageHref && (!list.contains(elc.toString())))
                        {
                        pq.add(new URL(elc.toString()));
                        list.add(elc.toString());
                        }
                        else if(elc instanceof PageWord)
                        {
                            vec.add(elc);
                        }
                    }
                     obj=new ObjectIterator<PageElementInterface>(vec);
                     i.addPage(ch, obj);
                }
        }
                
                catch (IOException ex) 
        {
            ex.printStackTrace();
        } 
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Priority Based Spider assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		
		return i;
	}

	/** Crawl the web, up to the default number of web pages.
	 */
	public Indexer  crawl() {
		// This redirection may effect performance, but its OK !!
		System.out.println("Crawling: "+u.toString());
		return  crawl(crawlLimitDefault);
	}

	/** The maximum number of pages to crawl. */
	public int crawlLimitDefault = 10;

}
