import java.util.HashMap;

public class CeasarCipherSolution {
    public static final int RADIX =26;

    static String caesarCipher(String str, int k) {
        char[] alphaLower = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] alphaUppper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        HashMap<Character, Character> map =  new HashMap<>();


        for (int i=0; i<RADIX; i++) {
            int idx = (i+k) % RADIX;
            map.put(alphaLower[i], alphaLower[idx]);
            map.put(alphaUppper[i], alphaUppper[idx]);
        }
        StringBuilder out = new StringBuilder();
        for (int j=0; j<str.length(); j++) {
            char chr = str.charAt(j);
            if (map.containsKey(chr)) {
                out.append(map.get(chr));
            } else {
                out.append(chr);
            }
        }

        return  out.toString();
    }

    public static void main(String[] args) {
        String  str = "middle-Outz";
        System.out.println(caesarCipher(str, 2)); // okffng-Qwvb
    }
}
