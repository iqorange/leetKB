import java.util.Scanner;

public class Test {
    public static void main(String args[]){
        Scanner sca = new Scanner(System.in);
        Solution solution = new Solution();

        System.out.println(solution.isHappy(sca.nextInt()));
    }
}
