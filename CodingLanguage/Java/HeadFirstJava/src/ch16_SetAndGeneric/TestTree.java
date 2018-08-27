package ch16_SetAndGeneric;
import java.util.*;

public class TestTree {
    public static void main(String[] args){
        new TestTree().go();
    }

    public void go(){
        Book b1 = new Book("How Cats Work");
        Book b2 = new Book("Remix your Body");
        Book b3 = new Book("Finding Emo");

        TreeSet<Book> tree = new TreeSet<Book>();
        tree.add(b1);
        tree.add(b2);
        tree.add(b3);
        System.out.println(tree.toString());

    }
}

class Book implements Comparable{
    String title;
    public Book(String t){
        title = t;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    @Override
    public int compareTo(Object b){
        Book book = (Book) b;
        return (title.compareTo(book.title));
    }

    @Override
    public String toString()
        {
            return "Book [title =" + title + "]";
        }

}