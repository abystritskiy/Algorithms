public class Decryptor {
    static String decrypt(String word) {

        StringBuffer descrypted = new StringBuffer();
        int step2 = 1;

        //        c   r   i   m   e
        // step1  99 114 105 109 101
        // step2 100 214 319 428 529
        // step3 100 110 111 116 113
        //       d   n   o   t   q

        for (int i=0; i<word.length(); i++) {
            int step1 = word.charAt(i) - step2;

            while (step1 <97) {
                step1 += 26;
            }

            descrypted.append(Character.toString((char)step1));
            step2 += step1;
        }

        return descrypted.toString();
    }

    public static void main(String[] args){
        System.out.println(decrypt("dnotq"));
    }
}
