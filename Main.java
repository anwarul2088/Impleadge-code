import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;



class Solution {
    public ArrayList<String> findAllConcatenatedWordsInADict(String[] words) {
        ArrayList<String> result = new ArrayList<>();
        Arrays.sort(words,(String a,String b)->a.length()-b.length());
        int counter=0;
        Tries root=new Tries('*');
        for(String word:words){
            if(!isEndThere(root,word,true))
                addStringTOTries(root,word);
            else
                result.add(word);
        }
        return result;
    }
    public void addStringTOTries(Tries root,String str){
        Tries temp = root;
        for(char ch:str.toCharArray()){
            if(temp.child[ch-'a']==null){
                temp.child[ch-'a']=new Tries(ch);
            }
            temp=temp.child[ch-'a'];
        }
        temp.isEnd=true;
    }
    public boolean isEndThere(Tries root,String str,boolean isSecond){
        Tries temp=root;
        boolean result=false;
        int count=0;
        for(char ch:str.toCharArray()){
            count++;
            if(temp.child[ch-'a']==null)
                return false;
            temp=temp.child[ch-'a'];
            if(temp.isEnd ) {
                if(str.length()>count)
                    result =result|| isEndThere(root, str.substring(count), false);
                else
                    result=true;
            }
            if(result)
                return true;

        }
        return result;
    }
}
class Tries{
    char ch;
    Tries[] child;
    boolean isEnd;
    Tries(char ch){
        this.ch=ch;
        this.child = new Tries[26];
        this.isEnd=false;
    }
}
public class Main {

    public static void main(String args[]) throws IOException {
        long start = System.currentTimeMillis();
        Solution obj = new Solution();
        List<String> result = new ArrayList<>();
        ArrayList<String> list;
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader("Input_01.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
        String[] str = new String[result.size()];

        for (int i = 0; i < result.size(); i++) {
            str[i] = result.get(i);
        }
        list = obj.findAllConcatenatedWordsInADict(str);
        String last = list.get(list.size() - 1);
        String secondlast = list.get(list.size() - 2);
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("Longest Compound Word:"+last);
        System.out.println("Second Longest Compound Word:"+secondlast);
        System.out.println("Time taken to process the input file: "+ elapsedTime+ " Milli Seconds");
    }

}

