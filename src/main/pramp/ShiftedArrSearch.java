public class ShiftedArrSearch {
    static int shiftedArrSearch(int[] shiftArr, int num) {
        int start = 0;
        int len = shiftArr.length;
        int end = len - 1;

        int shift = len - shiftingPoint(shiftArr);

        while (start <= end) {
            int mid = (end + start) / 2;
            int midCorrected = (len+mid-shift)%len;
            if (shiftArr[midCorrected] == num) {
                return mid;
            }
            if (num > shiftArr[midCorrected]) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        return -1;
    }

    static int shiftingPoint(int[] arr) {
        int start = 0;
        int end = arr.length-1;

        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] < arr[mid-1] || mid == 0) {
                return mid;
            }
            if (arr[start] > arr[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {4, 5, 9, 12, 17, 2};
        int num = 17;
//        System.out.println(shift);
        System.out.println(shiftedArrSearch(arr, num));
    }
}
