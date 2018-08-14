package ch16_SetAndGeneric;

import java.util.*;
import java.io.*;

public class Jukebox1 {
    ArrayList<String> songList = new ArrayList<String>(); //存储歌曲名称
    public static void main(String[] args){
        new Jukebox1().go();
    }

    /**
     * go()方法用于载入文件并列出内容
     */
    public void go(){
        getSongs();
        System.out.println(songList);
        Collections.sort(songList); //调用Collection静态的sort()然后在列出清单，第二次的输出会依照字母排序
        System.out.println(songList);
    }

    /**
     * getSongs 读取文件
     */
    void getSongs() {
        try{
            File file = new File("resource/SongList.txt");  //路径基于本项目的地址
            System.out.println(file.getAbsolutePath());
            BufferedReader reader = new BufferedReader(new FileReader (file));
            String line = null;
            while ((line = reader.readLine()) !=null){
                addSong(line);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 将SongList.txt中的内容解析出来
     * @param lineToParse
     */
    void addSong(String lineToParse){
        String[] tokens = lineToParse.split("/");   //splist()方法会用反斜线/来拆开歌曲的内容
        songList.add(tokens[0]);    //因为只需要歌曲名，所以只取第一项加入Songlist
    }
}
