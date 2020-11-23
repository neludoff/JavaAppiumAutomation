import java.util.ArrayList;
import java.util.List;

public class ListCheck {

    public static void main (String args[]){

        List<String> elements = new ArrayList<String>();
        elements.add("First");
        elements.add("Second");
        elements.add("Third");
        elements.add("Fourth");

        for(String anObject : elements){
            System.out.println(anObject);
        }


    }

}
