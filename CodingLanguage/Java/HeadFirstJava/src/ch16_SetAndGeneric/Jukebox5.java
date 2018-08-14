package ch16_SetAndGeneric;

import java.util.*;
import java.io.*;

public class Jukebox5 {
    ArrayList<Song> songList = new ArrayList<Song>();
    public static void main(String[] args) {
        new Jukebox5().go();
    }
    class ArtistCompare implements Comparator<Song>{    //创建并实现Comparator的内部类，
        public int compare (Song one, Song two){        //注意到类型参数和要比较的类型是相符的
            return one.getArtist().compareTo(two.getArtist());
        }
    }

    public void go(){
        getSongs();
        System.out.println(songList);
        Collections.sort(songList); //sort()方法值接收实现Comparable的传入参数
        System.out.println(songList);

        ArtistCompare artistCompare = new ArtistCompare();      //创建Comparator的实例
        Collections.sort(songList, artistCompare);              //调用sort()，传入list与Comparator对象
        System.out.println(songList);
    }

    void getSongs(){
        try{
            File file = new File ("resource/SongList.txt");
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
        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);   //使用解析出来的4项属性来创建Song对象并加入到list中
        songList.add(nextSong);
    }
}
