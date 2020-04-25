package leetPack.SolClass;

// 346. 数据流中的移动平均值
class MovingAverage {
    private int size;
    private int[] arr;
    private int point = 0;
    private int pack = 0;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        arr = new int[size];
    }

    public double next(int val) {
        arr[point] = val;
        point = (point+1)%size;
        if (pack != size) pack++;
        double sum = 0;
        for (int i=0;i<size;i++){
            sum += arr[i];
        }
        return sum/pack;
    }
}

