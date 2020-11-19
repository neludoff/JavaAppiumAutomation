import java.util.List;
import java.util.ArrayList;

public class ListExamples {

    public static void main (String[] args) {
        List mylist = new ArrayList();
        System.out.println(mylist.isEmpty());

        mylist.add("1 element");
        mylist.add("2 element");


        System.out.println(mylist.get(1));

        System.out.println(mylist.isEmpty());

    }

}
