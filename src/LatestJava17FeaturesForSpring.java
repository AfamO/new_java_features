/**
 * My comment oh
 * please
 */
public class LatestJava17FeaturesForSpring {
    public record Person(String name, String address) {
    }

    // Sealed class is good you know
    public abstract sealed class Pet permits Cat, Dog {
    }

    public final class Dog extends Pet {
    }

    public final class Cat extends Pet {
    }
    //public final class Goat extends  Pet {}


    public void textBlock() {
        String textBlock = """
                Hello this is a textblock
                from java
                and is part of java 17 features
                Thank you!
                """;
        System.out.println(textBlock);
    }

    public void callRecord() {
        Person person = new Person("CigaO", "No 2 Alhaji Amoo Street, Off Ogudu");
        Person secondPerson = new Person("Agbo", "No 2 Alhaji Amoo Street, Off Ogudu");
        System.out.println("Name:" + person.address());
        System.out.println("Address:" + person.name());
        System.out.println(person.equals(secondPerson));
        System.out.println("toString()::" + secondPerson.toString());
        secondPerson = new Person("CigaO", "No 2 Alhaji Amoo Street, Off Ogudu");
        System.out.println(person.equals(secondPerson));
        System.out.println(secondPerson.equals(secondPerson));
        //Wait here first

    }

    public static void main(String[] args) {
        LatestJava17FeaturesForSpring latestFeatures = new LatestJava17FeaturesForSpring();
        latestFeatures.callRecord();
        latestFeatures.textBlock();

    }
}
