public class TeenNumberChecker {
    public static void main (String[] args){
        System.out.println(hasTeen(9, 99, 19));
        System.out.println(hasTeen(23, 15, 42));
        System.out.println(hasTeen(22, 23, 34));
        System.out.println(isTeen(9));
        System.out.println(isTeen(13));
    }
    public static boolean hasTeen(int a, int b, int c){
        int[] nums = {a, b, c};
        for (int num : nums){
            if (num >= 13 && num <= 19) return true;
        }
        return false;
    }

    public static boolean isTeen(int a){
        return a >= 13 && a <= 19;
    }
}
