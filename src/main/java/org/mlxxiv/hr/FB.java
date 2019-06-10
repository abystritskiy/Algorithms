public class FB {
    public static void main(String[] args) {
        String s1 = "abcabcabcabc";
        System.out.println(solution(s1)); //4


        String s2 = "abccbaabccba";
        System.out.println(solution(s2)); //2

        String s3 = "aaaaaaaaa";
        System.out.println(solution(s3)); //9

        String s4 = "aabbaabb";
        System.out.println(solution(s4)); //9
    }

    public static int solution(String str   ) {
        char last = str.charAt(str.length() - 1);

        for (int i = 0; i<str.length(); i++) {
            if (str.charAt(i) == last && verify(str, str.substring(0, i+1))) {
                return str.length() / (i + 1);
            }
        }
        return 1;
    }

    public static boolean verify(String str, String sub) {
        if (str.length() % sub.length() != 0) return false;

        int i = 0;
        while (i < str.length()) {
            char strChr = str.charAt(i);
            char subChr = sub.charAt(i % sub.length());
            if (strChr != subChr) return false;
            i++;
        }

        return true;
    }
}
