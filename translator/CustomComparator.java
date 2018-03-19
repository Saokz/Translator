package translator;

/**
 * Custom comparator I got off of stackoverflow.
 * I don't know how this works, but it seems
 * a lot simpler than what I was going to do.
 */

public class CustomComparator implements java.util.Comparator<String> {

    public int compare(String s1, String s2) {
        return s2.length() - s1.length();
    }
}