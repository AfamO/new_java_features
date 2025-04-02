import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Java10Features {

    //public var unm = 12;
    public static void localTypeInference() throws URISyntaxException, IOException, InterruptedException {
        var message = "This is, Java 10";
        var map = new HashMap<Integer, String>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        System.out.println("Is this an instance of String? " + (message instanceof String));
        System.out.println("Message:: " + message);
        System.out.println("Is this an instance of Map? " + (map instanceof Map));
        System.out.println("var Map:: " + map);
    }

    public static void UnModifiableCollection() {
        List<Integer> copyList = List.copyOf(Arrays.asList(1, 2, 3));
        //copyList.add(4);
        List<Integer> evenList = Set.of(1, 2, 3, 4, 5, 6)
                .stream()
                .filter((integer -> integer % 2 == 0))
                .collect(Collectors.toUnmodifiableList());
        //evenList.add(8);
        System.out.println("My evenList==" + evenList);
    }

    private static void optionalOrElseThrow() {
        Integer firstEven = Set.of(1)
                .stream()
                .filter(i -> i % 2 == 0)
                .findFirst()
                .orElseThrow();
        System.out.println("FirstEven==" + firstEven);
    }


    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        localTypeInference();
        UnModifiableCollection();
        optionalOrElseThrow();
    }


}
