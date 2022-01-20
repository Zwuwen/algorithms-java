package 高频题;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 *
 * @author MJ
 */
public class _42_接雨水 {

    /**
     * 空间复杂度O(1)，时间复杂度O(n)
     * 1.左右交替求得最大值
     * 2.如果当前比较的较大值,即是已经比较过的最大值，次大值一定在另一边(见1，可能是当前比较的较小值)
     * 3.因为比较过的最大值在右边，且次大值在左边，所以次大值和当前比较的较小值之差，就是
     */
    public int trap(int[] height) {
        if(height==null || height.length==0) return  0;
        int lowerMax = 0;
        int water =0;
        int leftIndex=0;
        int rightIndex=height.length-1;
        int lower=0;
        while(leftIndex<=rightIndex){
            lower = height[leftIndex]<=height[rightIndex]?height[leftIndex++]:height[rightIndex--];
            lowerMax = Math.max(lowerMax,lower);
            water += lowerMax - lower;
        }
        System.out.println(lower+" "+lowerMax+" "+" "+water);;
        return lower;
    }

    public int trap1(int[] height) {
        if (height == null || height.length == 0) return 0;

        int lastIdx = height.length - 2;

        int[] rightMaxes = new int[height.length];
        for (int i = lastIdx; i >= 1; i--) {
            rightMaxes[i] = Math.max(rightMaxes[i + 1], height[i + 1]);
        }

        // 遍历每一根柱子，看看每一根柱子上能放多少水
        int water = 0, leftMax = 0;
        for (int i = 1; i <= lastIdx; i++) {
            leftMax = Math.max(leftMax, height[i - 1]);
            // 求出左边最大、右边最大中的较小者
            int min = Math.min(leftMax, rightMaxes[i]);
            // 说明这根柱子不能放水
            if (min <= height[i]) continue;
            // 说明这根柱子能放水
            water += min - height[i];
        }

        return water;
    }

    public int trap0(int[] height) {
        if (height == null || height.length == 0) return 0;

        int lastIdx = height.length - 2;
        int[] leftMaxes = new int[height.length];
        for (int i = 1; i <= lastIdx; i++) {
            leftMaxes[i] = Math.max(leftMaxes[i - 1], height[i - 1]);
        }

        int[] rightMaxes = new int[height.length];
        for (int i = lastIdx; i >= 1; i--) {
            rightMaxes[i] = Math.max(rightMaxes[i + 1], height[i + 1]);
        }

        // 遍历每一根柱子，看看每一根柱子上能放多少水
        int water = 0;
        for (int i = 1; i <= lastIdx; i++) {
            // 求出左边最大、右边最大中的较小者
            int min = Math.min(leftMaxes[i], rightMaxes[i]);
            // 说明这根柱子不能放水
            if (min <= height[i]) continue;
            // 说明这根柱子能放水
            water += min - height[i];
        }

        return water;
    }
    public static void main(String[] args){
        _42_接雨水 foo = new _42_接雨水();
        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        foo.trap(heights);
    }

}
