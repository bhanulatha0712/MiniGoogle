/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine.dictionary;

import java.net.URL;
import java.util.Comparator;

/**
 *
 * @author Administrator
 */
public class Comp<URL> implements Comparator<URL>
{
    @Override
    public int compare(URL u1, URL u2) 
    {
        int c1,c2;
        c1=count(u1);     
        c2=count(u2);
        return (c1-c2);
    }
   
   public int count(URL url)
    {
       int count = 0;
        String str=url.toString();
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i) =='/')
                count++;
        }
        return count;
    }
    
}
