package leetPack.Arrays;

public class BinarySearch {
    public BinarySearch(){

    }

    public static int binarySearch(Comparable[] arr, int n, Comparable target){
        // [l, r]之间寻找target，保证循环不变量
        int l = 0;
        int r = n-1;
        // 只要可以查找就继续查找
        while (l <= r){
            // 防止整型溢出问题
            int mid = l + (r - l)/2;
            // 找到了这个元素
            if (arr[mid].compareTo(target) == 0) {
                return mid;
            }
            if (arr[mid].compareTo(target) < 0){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = (int)Math.pow(10, 7);
        Integer data[] = Util.generateOrderedArray(n);

        long startTime = System.currentTimeMillis();
        for(int i = 0 ; i < n ; i ++) {
            if (i != binarySearch(data, n, i)) {
                throw new IllegalStateException("find i failed!");
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Binary Search test complete.");
        System.out.println("Time cost: " + (endTime - startTime) + " ms");
    }
}
