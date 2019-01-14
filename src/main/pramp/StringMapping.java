/*
* Facebook interview question
*/
import java.util.HashMap;
import java.util.Map;

public class StringMapping {
    public static final int MAX = 26;

    public int getNum(String str, int start, Integer total) {
        if (str.length() == 0 || str.charAt(0) == '0') {
            return 0;
        }
        if (start >= str.length()-1) {
            return total;
        }

        getNum(str, start+1, total);

        int twoDigits = Integer.valueOf(str.substring(start, start+2));

        if (twoDigits <= MAX ) {
            total++;
            getNum(str, start+2, total);
        }

        return total;
    }

    public static void main(String[] args) {
        StringMapping sm = new StringMapping();
        System.out.println(sm.getNum("3",0,1 )); // 1
        System.out.println(sm.getNum("",0,1 )); // 0
        System.out.println(sm.getNum("12345",0,1 )); // 2
        System.out.println(sm.getNum("27345",0,1 )); // 1
        System.out.println(sm.getNum("011",0,1)); // 0
    }
}
