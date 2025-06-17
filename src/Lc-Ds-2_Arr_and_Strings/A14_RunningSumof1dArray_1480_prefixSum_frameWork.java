public class A14_RunningSumof1dArray_1480_prefixSum_frameWork {
    public int[] runningSum(int[] nums){
        int[] p = new int[nums.length];//p=prefix array.

        p[0] = nums[0];
        for(int i = 1 ; i < nums.length ; ++i)
        {
            p[i] = nums[i] + p[i-1];
        }
        return p;
    }
}