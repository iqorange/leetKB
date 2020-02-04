public class Solution {
    public boolean isHappy(int n) {
        int t = n;
        while (t != 1 && t != 4 && t!=58){
            String str = Integer.toString(t);
            t = 0;
            for(int i=0;i<str.length();i++){
                int s = Integer.parseInt(String.valueOf(str.charAt(i)));
                t += s*s;
            }
        }
        if(t == 1){
            return true;
        }else{
            return false;
        }
    }
}
