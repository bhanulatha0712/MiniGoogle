/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */

package searchengine.spider;


import java.io.*;
import java.net.URL;

import searchengine.indexer.Indexer;


/** A user interface for web-crawling objects.
 * Usage : java CrawlerDriver url saveFile [list|custom] [limit]
 */
public class CrawlerDriver 
{
	/** A main entry point, to be used by web administrators, for
	creating web indexes.
	 */
	public static void main (String[] args) {
		try {
			URL u;
			FileOutputStream isaveFile;
                        FileInputStream getFile;
                        System.setProperty("java.net.useSystemProxies", "true");
                        //System.setProperty("http.proxyHost", "10.10.10.6");
                        //System.setProperty("http.proxyPort", "8080");
			BreadthFirstSpider web = null;

			if (args.length >= 2) {
				// Create a web crawler
				u = new URL(args[0]);
                                //u = new URL("http://wiki2012.msitprogram.net");
                               //u = new URL("file:///C:/Documents%20and%20Settings/Administrator/Desktop/main%20tasks/mini%202/201285041_DS11_W1_M17/HTML.html");
                                //u = new URL("http://www.google.com");
				String indexMode = args[2];
				indexMode = indexMode.toLowerCase();
				
				// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
				// list - Dictionary Structure based on Linked List 
				// myhash - Dictionary Structure based on a Hashtable implemented by the students
				// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
				// avl - Dictionary Structure based on AVL Tree implemented by the students

				if (indexMode.equals("list") || indexMode.equals("hash") || indexMode.equals("myhash")
						|| indexMode.equals("bst") || indexMode.equals("avl")) 
					web = new BreadthFirstSpider(u, new Indexer(indexMode));

				else {
					System.out.println("Invalid index mode - use either \"list\" or \"hash\"");
					System.exit(1);
				}

				// Open the index save file
				isaveFile = new FileOutputStream(args[1]);
                         getFile=new FileInputStream(args[1]);

			}
			else 
			{
				System.out.println("Usage: CrawlerDriver url index-saveFile [hash | list] [crawl limit]");
				return;
			}

			// Check for a page limit
			if (args.length > 3)
				web.crawlLimitDefault = Integer.parseInt(args[3]);

			// Crawl the web site
			Indexer index = web.crawl();

			// Save the index to the specified file
			index.save(isaveFile);
                        System.out.println(" check 1 ");
                        
                   index.restore(getFile);
                    System.out.println(" check 2 ");

		} catch (IOException e) {
                        System.out.println(" error is : "+e.getCause());
                        System.out.println(" other error is : "+e.getMessage());
                        e.printStackTrace();
			System.out.println("Bad file or URL specification");
		} catch (NumberFormatException e) {
			System.out.println("Bad page limit.");
		}
	}
}

