package ch16_SetAndGeneric;

/**
 * This program demonstrates TreeSet
 */

import java.util.*;
import java.io.*;

public class Jukebox8 {
    ArrayList<Song> songList = new ArrayList<Song>();
    int val;

    public static void main(String[] args){
        new Jukebox8().go();
    }

    public void go(){
        getSongs();
        System.out.println(songList);
        Collections.sort(songList);
        System.out.println(songList);
        TreeSet<Song> songSet = new TreeSet<Song>();    //调用没有参数的构造函数来用TreeSet取代HashSet意味着以对象的compareTo()方法来进行排序
        songSet.addAll(songList);          //使用addAll()可以把对象全部加入
        System.out.println(songSet);
    }

    void getSongs(){
        try{
            File file = new File("resource/SongListMore.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) != null ){
                addSong(line);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    void addSong(String lineToParse){
        String[] tokens = lineToParse.split("/");
        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
        songList.add(nextSong);
    }
}
