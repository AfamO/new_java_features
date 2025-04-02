import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Java11Features {

    public static void newString() {
        String multiLineString = "Afam \n \n sincerely try and help \n \n developers\n write codes easily in \n \nJava using NLP  ";
        System.out.println("Original::" + multiLineString.lines().collect(Collectors.toList()));
        String repeatedName = "AfamO ".repeat(3);
        List<String> lines = multiLineString.lines()
                .filter((line) -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());
        System.out.println("Final:: " + lines + " RepeatedName::" + repeatedName);
        //assert (lines.cont)
    }

    public void containsWhiteSpace() {
        String multiLineString = "Afam \n \n sincerely try and help \n \n developers\n write codes easily in \n \nJava using NLP  ";

        Pattern pattern = Pattern.compile("\\s");
        //multiLineString = "Great";
        Matcher matcher = pattern.matcher(multiLineString);
        boolean found = matcher.find();
        System.out.println("Contains WhiteSpace?::" + found);
        multiLineString = "                      ";
        System.out.println("Is it ONLY whitespace?:: " + multiLineString.matches("^\\s*$"));
    }

    public void newFileMethods(String pathDir) throws IOException {
        Path dirPath = Paths.get(pathDir);
        System.out.println("File System Separator==" + dirPath.getFileSystem().getSeparator());
        System.out.println("File System Provider==" + dirPath.getFileSystem().provider().toString());
        System.out.println("Parent==" + dirPath.getParent());
        System.out.println("Root==" + dirPath.getRoot());
        System.out.println("NameCount==" + dirPath.getNameCount());
        System.out.println("InnerMost FileName==" + dirPath.getFileName().getName(0));
        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxr-x---");
        FileAttribute<Set<PosixFilePermission>> fileAttribute = PosixFilePermissions.asFileAttribute(permissions);
        BasicFileAttributes basicFileAttributes = Files.readAttributes(dirPath, BasicFileAttributes.class);
        Path tempFile = Files.createTempFile(dirPath, "demo", ".txt");
        tempFile = Files.write(tempFile, "Sample Text from me".getBytes());


        String fileContent = Files.readString(tempFile);
        System.out.println("Read File==" + fileContent);

    }

    private int calcualateNumberOfLinesOfJavaCodeWithoutCommentsAndWhiteSpace(String filePath) {
        Path path = Paths.get(filePath);
        Long startTime = 0l;
        int codeLineCounter = 0;
        try {

            String fileContent = Files.readString(path);
            System.out.println("Codes Red==" + fileContent);
            startTime = System.currentTimeMillis();
            System.out.println("Start Time::" + LocalTime.now());
            List<String> codeLines = fileContent.lines()
                    .collect(Collectors.toList());
            for (String line : codeLines) {
                if (line.startsWith("/") || (line.startsWith("//")) || (line.startsWith("*")) || (line.matches("^\\s*$"))
                        || line.contains("*/") || line.contains("//")) {
                } else
                    codeLineCounter++;

            }
        } catch (IOException ioException) {
            System.out.println("OOOps! can't read the file. Error Message:" + ioException.getMessage());
        }
        System.out.println("Number of LineOfCodes==" + codeLineCounter);
        Long endTime = System.currentTimeMillis();
        System.out.println("Time Taken::" + (endTime - startTime) / 1000_000);
        return codeLineCounter;
    }

    public void collectionToArray() {
        List sampleList = Arrays.asList("Java", "Kotlin");
        String[] stringArray = (String[]) sampleList.toArray(String[]::new);
        sampleList.forEach(System.out::println);
        for (String arr : stringArray)
            System.out.print(arr + " ");
        System.out.println();
    }


    public void notPredicate() {
        List<String> sampleList = Arrays.asList("Java", "\n \n", "Kotlin", " ");
        List withoutBlanks = sampleList.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        System.out.println(withoutBlanks);
    }

    public void httpClient(String url) throws IOException, InterruptedException, TimeoutException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(30))
                .build();

        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url))
                .GET()
                .headers("name", "Ciga", "age", "19")
                .header("language", "en")
                .header("os", "windows")
                //.uri(URI.create(url))
                .build();
        HttpRequest.BodyPublishers.ofString("Great");
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Result from Server::" + httpResponse.body() + " Request Headers==" + httpRequest.headers().map() + " Response Headers::" + httpResponse.headers());

    }


    public static void main(String[] args) {
        Java11Features Java11Features = new Java11Features();

        System.out.println("User Home::" + System.getProperty("user.home"));
        Java11Features.containsWhiteSpace();
        try {
            Java11Features.newFileMethods("C:\\Users\\Afam O\\IdeaProjects\\Java 11 New Features\\src");
            Java11Features.httpClient("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=IBM&apikey=demo");
            Java11Features.collectionToArray();
            Java11Features.notPredicate();
            Java11Features.calcualateNumberOfLinesOfJavaCodeWithoutCommentsAndWhiteSpace("C:\\Users\\Afam O\\IdeaProjects\\Java 11 New Features\\src\\Example4.java");
            System.out.println("Preparing Java 9:::");


        } catch (IOException e) {
            System.out.println("OOps! Error occured in the file operation::" + e.getLocalizedMessage());
        } catch (InterruptedException e) {
            System.out.println("OOps! An InterruptedException occured");
        } catch (TimeoutException e) {
            System.out.println("OOps! A TimeoutException occured");
        }
        //System.out.println("All properties:" + System.getProperties());

        try {
            System.out.println("Great");
            ClassLoader.getSystemClassLoader().loadClass("");
        } catch (ClassNotFoundException exception) {
            System.out.println("");
        }
        /* // */
        stringManip();
        System.out.println("Frequency of 1==" + Collections.frequency(Arrays.asList(1, 2, 2, 1, 5, 2, 3, 1, 2), 1));
        System.out.println("Minimum Value in the List::" + Arrays.asList(8, 0, 1, 6, 1, 4, 2).stream().mapToInt(Integer::intValue).min());
        
    }

    public class OuterList {
        public class InteriorList {
            public List<String> list = new ArrayList<>();
        }
    }

    public static void stringManip() {
        String multiLineString = "Baeldung helps \n \n developers \n explore Java.";
        List<String> lines = multiLineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());
        lines.add("Great");
        System.out.println("Lined Strings==" + lines);

    }

}
