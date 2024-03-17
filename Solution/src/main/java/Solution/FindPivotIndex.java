package Solution;

public class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int result = -1;
        if ((nums.length >= 1) && (nums.length <= 10000)) {
            for (int i = 0; i < nums.length; i++) {
                if ((nums[i] >= -1000) && (nums[i] <= 1000)) {
                    int x = nums[i];
                    int sumL = 0;
                    for (int j = 0; j <= i - 1; j++) {
                        sumL = sumL + nums[j];
                    }
                    int sumR = 0;
                    for (int k = i + 1; k < nums.length; k++) {
                        sumR = sumR + nums[k];
                    }
                    if (sumL == sumR) {
                        return i;
                    }
                } else {
                    return result;
                }
            }
        }
        return result;
    }
}
