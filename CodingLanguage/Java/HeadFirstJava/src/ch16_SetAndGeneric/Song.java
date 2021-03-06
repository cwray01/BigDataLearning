package ch16_SetAndGeneric;

class Song implements Comparable<Song>{
    //对应四种属性的四个实例变量
    String title;
    String artist;
    String rating;
    String bpm;

    public boolean equals(Object aSong){    //要被比较的对象
        Song s = (Song) aSong;
        return getTitle().equals(s.getTitle());     //因为歌名是String,且String本来就覆盖过的equals(),所以我们可以调用它
    }

    public int hashCode(){
        return title.hashCode();    //String也有覆盖过的hashCode(),注意到hashCode()与equals()使用相同的实例变量-title
    }

    public int compareTo(Song s){
        return title.compareTo(s.getTitle());
    }

    //变量都会在创建时从构造函数中设定
    Song(String t, String a, String r, String b){
        title = t;
        artist = a;
        rating = r;
        bpm = b;
    }

    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }

    public String getRating(){
        return rating;
    }

    public String getBpm(){
        return bpm;
    }

    /**
     * 将toString()覆盖过，让它返回歌名
     * @return
     */
    public String toString(){
        return title;
    }
}
