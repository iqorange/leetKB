package leetPack.SolClass;

import AdvancedDataStructure.SegmentTree.SegmentTree;

// 307. 区域和检索 - 数组可修改
// 线段树解法
class NumArray3 {
    private SegmentTree<Integer> segmentTree;
    public NumArray3(int[] nums) {
        if (nums.length>0){
            Integer[] data = new Integer[nums.length];
            for (int i=0;i<nums.length;i++){
                data[i] = nums[i];
            }
            segmentTree  = new SegmentTree<Integer>(data, (a, b) -> a + b);
        }
    }

    public void update(int i, int val) {
        if (segmentTree == null){
            throw new IllegalArgumentException("Segment Tree is null");
        }
        segmentTree.set(i, val);
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null){
            throw new IllegalArgumentException("Segment Tree is null");
        }
        return segmentTree.query(i, j);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */