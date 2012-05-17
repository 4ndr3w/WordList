package me.lobos.andrew.spanish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrew
 */
public class WordList
{
    private HashMap map;
    
    public WordList()
    {
        map = new HashMap();
    }
    
    public void addWord(String word, String definition)
    {
        map.put(word, definition);
    }
    
    public String getDefinitionForWord(String word)
    {
        return (String) map.get(word);
    }
    
    public String getRandomWord()
    {
        Object[] words = map.keySet().toArray();
        int word = (int)(Math.random()*map.size());
        return (String) words[word];
    }
    
    public void remove(String word)
    {
        map.remove(word);
    }
    
    public int size()
    {
        return map.size();
    }
   
    static void play(WordList list)
    {
        System.out.println("Now using wordlist: "+list.getClass().getSimpleName());
        
        WordList words = new Chapter5();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        int timesWrong = 0;
        while(words.size() != 0)
        {
            String word = words.getRandomWord();
            while(true)
            {
                System.out.print(word+": ");
                String input;
                
                try {
                    input = console.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(WordList.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
                
                if ( words.getDefinitionForWord(word).equals(input) )
                {
                    System.out.println("Correct");
                    words.remove(word);
                    break;
                }
                System.out.println("Incorrect");
                timesWrong++;
                
                if ( timesWrong > 3 )
                {
                    System.out.println("The answer is "+words.getDefinitionForWord(word));
                    timesWrong = 0;
                    break;
                }
            }
        }
        System.out.println("You are finished with the "+list.getClass().getSimpleName()+" wordlist.");
    }
    
    
}
