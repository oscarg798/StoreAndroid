package co.com.store;

import junit.framework.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by oscarg798 on 12/17/17.
 */

public class Test {


    @org.junit.Test
    public void test1() {

        int[] a = new int[]{1, 3, 4, 5, 5, 1, -3};
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (i != a.length - 1) {
                for (int j = i + 1; j < a.length; j++) {
                    if (a[i] != a[j]) {
                        if (canBeAHillOrValley(i > 0 ? i - 1 : null, i, j - 1, a)) {
                            count++;
                            i = j;
                            break;
                        }
                    }
                }
            } else {
                count++;
            }

        }

        Assert.assertEquals(3, count);
    }


    public boolean canBeAHillOrValley(Integer previous, int p, int q, int[] a) {
        if (q + 1 < a.length) {
            if (a[q] < a[q + 1] && (previous == null || previous > a[q])) {
                return true;
            } else if (a[q] > a[q + 1] && (previous == null || previous < a[q])) {
                return true;
            }
        }


        return false;

    }
}
