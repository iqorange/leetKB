package leetPack;

import SegmentTree.SegmentTree;

// 303. 区域和检索 - 数组不可变
class NumArray {
    private SegmentTree<Integer> segmentTree;
    public NumArray(int[] nums) {
        if (nums.length>0){
            Integer[] data = new Integer[nums.length];
            for (int i=0;i<nums.length;i++){
                data[i] = nums[i];
            }
            segmentTree  = new SegmentTree<Integer>(data, (a, b) -> a + b);
        }
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
 * int param_1 = obj.sumRange(i,j);
 */