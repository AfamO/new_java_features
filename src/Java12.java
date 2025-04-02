import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java12 {
    private static void newStringMethods() {
        String text = "Hello Baeldung!\nThis is Java 12 article.";
        text = text.indent(4);
        print("Indented String: \n" + text);
        text = text.indent(-10);
        print("Neg Indented String: \n" + text);
        new PrintWriter((Writer) null);

        text = "Baeldung";
        String transformed = text.transform(value -> new StringBuilder(value).reverse().toString());
        print("Transformed String: \n" + transformed);
    }

    private static void fileMisMatch() {
        try {
            Path filePath1 = Files.createTempFile("file1", ".txt");
            Path filePath2 = Files.createTempFile("file2", ".txt");
            Files.writeString(filePath1, "Java 12 Article");
            Files.writeString(filePath2, "Java 12 Article");
            print("Mismatch Value:" + Files.mismatch(filePath1, filePath2));
            Path filePath3 = Files.createTempFile("file3", ".txt");
            Path filePath4 = Files.createTempFile("file4", ".txt");
            Files.writeString(filePath3, "Java 12 Article");
            Files.writeString(filePath4, "Java 12 Tutorial");
            print("Mismatch Value:" + Files.mismatch(filePath3, filePath4));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void teeingCollector() {
        double mean = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.teeing(Collectors.summingDouble(i -> i),
                        Collectors.counting(), (sum, count) -> sum / count));
        print("Average from Teeing Collector==" + mean);
    }

    public static void compactNumberFormat() {
        NumberFormat likesShort = NumberFormat.getCompactNumberInstance(new Locale("en", "US"),
                NumberFormat.Style.SHORT);
        likesShort.setMaximumFractionDigits(2);
        NumberFormat likesLong = NumberFormat.getCompactNumberInstance(new Locale("en", "US"),
                NumberFormat.Style.LONG);
        likesLong.setMaximumFractionDigits(2);

        print("Format in Short Style:" + likesShort.format(5605230));
        print("Format in Long Style:" + likesLong.format(5605230));
    }

    public static void newSwitch() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        String typeOfDay = "";
        switch (dayOfWeek) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                typeOfDay = "Working Day";
            case SATURDAY:
            case SUNDAY:
                typeOfDay = "Day off";
        }
        print("Old Style Switch-Day of Week:" + typeOfDay);
        // new switch with java 12
        typeOfDay = switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Working Day!";
            case SATURDAY, SUNDAY -> "Day Off";
        };
        print("Java 12 New Style Switch-Day of Week:" + typeOfDay);
        switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> {
                //More complex logic
                print("Old Style Switch-Day of Week:" + "Working Day!");
            }
            case SATURDAY, SUNDAY -> print("Old Style Switch-Day of Week:" + "Day Off");
        }
    }

    public static void print(String string) {
        System.out.println(string);
    }

    public static void patternMatchingForInstanceOf() {
        Object obj = "Hello world";
        if (obj instanceof String) {
            String s = (String) obj;
            int length = s.length();
            print("Before Java12==" + length);
        }
        //After Java 12
        if (obj instanceof String s) {
            int length = s.length();
            new GregorianCalendar();
            SSLSocketFactory sslSocketFactory;
            String ss = Integer.valueOf(1).toString();

            print("After Java12==" + Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR));
        }


    }

    class Me<Type> {
        Me() {
          
        }
    }

    public static void main(String[] args) {
        newStringMethods();
        fileMisMatch();
        teeingCollector();
        compactNumberFormat();
        newSwitch();
        patternMatchingForInstanceOf();
    }
}
