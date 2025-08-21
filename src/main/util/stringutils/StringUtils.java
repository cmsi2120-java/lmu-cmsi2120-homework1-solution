package stringutils;

public class StringUtils {
    
    public static boolean hasSequence (String corpus, String query) {
        if (query.length() == 0) { return true; }
        
        int index = 0;
        for (char c : corpus.toCharArray()) {
            if (c == query.charAt(index)) {
                index++;
            }
            if (index == query.length()) {
                return true;
            }
        }
        return false;
    }
    
    public static String sentCap (String sents) {
        char[] chars = sents.toCharArray();
        boolean capNext = false;
        
        for (int i = 0; i < chars.length; i++) {
            char currChar = chars[i];
            if (capNext && currChar != ' ') {
                chars[i] = Character.toUpperCase(currChar);
                capNext = false;
            }
            if (currChar == '.') {
                capNext = true;
            }
        }
        
        return new String(chars);
    }
    
    public static String getNthMatch (String sent, String query, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        if (query.length() == 0 || sent.length() == 0) {
            throw new IllegalArgumentException("sent and query must not be empty");
        }
        
        String[] words = sent.split(" ");
        
        for (String word : words) {
            if (word.toLowerCase().equals(query.toLowerCase())) {
                if (n == 0) {
                    return word;
                }
                n--;
            }
        }
        
        return null;
    }
    
}
