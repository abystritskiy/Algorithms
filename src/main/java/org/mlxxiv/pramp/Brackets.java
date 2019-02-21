public class Brackets {
    static int bracketMatch(String text) {
        int flag = 0;
        int results = 0;

        for (int i = 0; i<text.length(); i++) {
            char chr = text.charAt(i);
            if (chr == '(') {
                flag++;
            }  else {
                if (flag==0) {
                    results++;
                } else {
                    flag--;
                }
            }
        }
        results += flag;
        return results;
    }

    //

}
