import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Java9Fetures {

    public static void httpClientJava9() throws URISyntaxException, IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/get"))
                .GET()
                .build();
        HttpResponse<String> httpResponse = HttpClient.newHttpClient()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Results from Java 9==" + httpResponse.body());
    }

    public static void processAPI() {
        ProcessHandle processHandle = ProcessHandle.current();
        long pid = processHandle.pid();
        ProcessHandle.Info procInfo = processHandle.info();

        Optional<String[]> args = procInfo.arguments();
        Optional<String> cmds = procInfo.commandLine();

        Optional<Instant> startTime = procInfo.startInstant();
        Optional<Duration> cpuUsage = procInfo.totalCpuDuration();

        System.out.println("Process Args==" + List.of(args));
        System.out.println("Process CommandLines==" + List.of(cmds));
        System.out.println("Process Commands==" + List.of(procInfo.command()));
        System.out.println("Process StartTime==" + startTime);
        System.out.println("Process cpuUsage==" + cpuUsage + " PID==" + pid);
        List<ProcessHandle> childProc = ProcessHandle.current().children().toList();
        System.out.println("Children Process::");
        childProc.forEach(procHandle -> {
            System.out.println("Process PID==" + procHandle.pid());
            System.out.println("Process PID==" + procHandle.destroy());
        });

    }


    public static void tryWithResourcesJava9() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Afam O\\IdeaProjects\\Java 11 New Features\\src\\Example4.java"));
        try (bufferedReader) {

            String line;
            System.out.println("<<<<Reading>>>>");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("<<<<End>>>>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void tryWithResourcesJava7() {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Afam O\\IdeaProjects\\Java 11 New Features\\src\\Example4.java"))) {

            String line;
            System.out.println("<<<<Reading>>>>");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("<<<<End>>>>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void innerClassWithDiamonds() {
        FooClass<Integer> fooClass = new FooClass<>(1) {

        };
        FooClass<? extends Integer> fco = new FooClass<>(2) {

        };

    }

    public static void optionalStream() {
        List<Optional<Integer>> optionalList = Arrays.asList(Optional.of(1), Optional.of(2), Optional.of(3), Optional.of(4));
        List<Integer> filteredList = optionalList
                .stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println("Filtered OptionalList==" + filteredList);

    }

    public static void immutableSet() {
        Set<String> sets = new TreeSet<>(Arrays.asList("Mango", "Orange", "Pawpaw", "Orange", "Guava"));
        System.out.println("Tree Sets==" + sets);
        Set<String> stringSet = Set.of("Mango", "Pawpaw", "Orange", "Guava");
        System.out.println("Immutable Sets==" + stringSet);
    }

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        httpClientJava9();
        processAPI();
        tryWithResourcesJava7();
        tryWithResourcesJava9();
        new Java9Fetures().innerClassWithDiamonds();
        System.out.println(InterfaceWithPrivateMethods.staticPrivate());
        immutableSet();
        optionalStream();
    }

    interface InterfaceWithPrivateMethods {

        private static String staticPrivate() {
            Socket socket;
            return "my static private method interface";
        }

        private String instantPrivate() {
            return "instant private";
        }

        default void check() {
            String result = staticPrivate();
            InterfaceWithPrivateMethods interfaceWithPrivateMethods = new InterfaceWithPrivateMethods() {

            };
            result = interfaceWithPrivateMethods.instantPrivate();
        }
    }

    class FooClass<T> {

        public FooClass(T i) {
            System.out.println("Welcome to my Anonymoucsl class. Id==" + i);
        }


    }


}
