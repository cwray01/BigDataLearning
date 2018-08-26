package ch16_SetAndGeneric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.*;
import java.io.*;

public class Jukebox6 {
    ArrayList<Song> songList = new ArrayList<Song>();
    public static void main(String[] args) {
        new Jukebox6().go();
    }

    public void go(){
        getSongs();
        System.out.println(songList);   //第一次打印，按照list的顺序
        Collections.sort(songList); //sort()方法值接收实现Comparable的传入参数
        System.out.println(songList);   //第二次打印，歌手名字首字母排序

        HashSet<Song> songSet = new HashSet<Song>();    //创建参数化的HashSet来保存Song
        songSet.addAll(songList);   //addAll可以复制其他集合的元素，效果跟一个一个加进去一样
        System.out.println(songSet);    //第三次打印，根据点歌次数
    }

    void getSongs(){
        try{
            File file = new File ("resource/SongListMore.txt");
            System.out.println(file.getAbsolutePath());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null){
                addSong(line);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    void addSong(String lineToParse){


        String[] tokens = lineToParse.split("/");
        //System.out.println(tokens[3]);
        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);   //使用解析出来的4项属性来创建Song对象并加入到list中
        songList.add(nextSong);
    }
}

