import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Functional {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("hi", "ib", "say", "hello", "world");

        // P1
//        for (String word : words) {
//            System.out.println("  " + word);
//        }
        words.forEach(w -> System.out.println("  " + w));

        System.out.println();

        // P2
        words.forEach(System.out::println);

        System.out.println();

        // P3
        words.stream()
                .map(s -> s + "!")
                .map(s -> s.replace("i", "eye"))
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println();

        // P4
        words.stream()
                .filter(w -> w.length() < 4)
                .filter(w -> w.contains("b"))
                .filter(w -> (w.length() % 2) == 0)
                .forEach(System.out::println);

        System.out.println();

        // P5
//        Stream<String> p5s =
//                words.stream()
//                        .map(String::toUpperCase)
//                        .filter(w -> w.length() < 4);
//        p5s.filter(w -> w.contains("E"))
//                .findFirst().ifPresent(System.out::println);          // doesn't work - stream has already been operated upon or closed
//        p5s.filter(w -> w.contains("Q"))
//                .findFirst().ifPresent(System.out::println);
        p5f(words.stream(), "E").ifPresent(System.out::println);
        p5f(words.stream(), "Q").ifPresent(System.out::println);

        System.out.println();

        // P6
        System.out.println(words.stream()
                .reduce("", (l, r) -> l.toUpperCase() + " " + r.toUpperCase()));          // l is the accumulator,

        System.out.println();

        // P7
                words.stream()
                        .map(String::toUpperCase)
                        .reduce((l, r) -> l + " " + r)          // l is the accumulator,
                        .ifPresent(System.out::println);

        System.out.println();

        // P8
        words.stream()
                .reduce((l, r) -> l + "," + r)
                .ifPresent(System.out::println);

        System.out.println();

        // P9
        System.out.println(words.stream()
                .map(String::length)
                .reduce(0, Integer::sum));    //  same as    .reduce(0, (l, r) -> l + r)

        System.out.println();

        // P10
        System.out.println(words.stream()
                .filter(w -> w.contains("h"))
                .map(ignored -> 1)                  // we count the words, not the characters
                .reduce(0, Integer::sum));
    }

    public static Optional<String> p5f(Stream<String> input, String filterS) {      // Optional is a container object used to contain not-null objects.
        return input.map(String::toUpperCase)
                .filter(w -> w.length() < 4)
                .filter(w -> w.contains(filterS))
                .findFirst();
    }
}
