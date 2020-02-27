package lanqiao;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        int n = sca.nextInt();
        int m = sca.nextInt();
        for (int i=0;i<n;i++){
            for (int j=-i;j<m-i;j++){
                System.out.print((char)('A'+Math.abs(j)));
            }
            System.out.println();
        }
    }
}
