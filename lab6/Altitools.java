/*
 * CSCI 1913
 * Lab 06
 * Author: Mohamed Dek Hussein
 * */

// keep this line.
import java.lang.Math;
// this line will let you use function from the Java math package:
// details here: https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/lang/Math.html
// but a few functions you might need:
// Math.abs(number)
// Math.atan2(y, x)
// Math.pow(a,b) (which equals a^b)
// Math.toDegrees(radians)
// Math.sqrt(number)
// Use these roughly as written (so include the "Math." part)

public class Altitools {

    /**
     * Computes the steepest angle (Raising or falling) in a mountain range
     *
     * @param mountainRange an array of doubles representing the heights of a mountain range. Will not be modified
     * @return the steepest upward/downward angle in the mountain range as a positive value measured in degrees.
     */
    public static double steepest_angle(double[] mountainRange) {
        double steepest_diff = 0;
        double angle = 0;
        for (int i = 0; i < mountainRange.length - 1; i++) {
            if (Math.abs(mountainRange[i + 1] - mountainRange[i]) > steepest_diff) {
                steepest_diff = Math.abs(mountainRange[i + 1] - mountainRange[i]);
            }
            angle = Math.toDegrees(Math.atan2(steepest_diff, 1));
        }
        return angle;
    }

    /**
     * Get the total "travel distance" over a mountain range
     *
     * @param mountainRange an array of doubles representing the heights of a mountain range.  Will not be modified
     * @return the travel distance -- a non-negative double.
     */
    public static double total_distance(double[] mountainRange) {
        if (mountainRange.length == 1) {
            return 0;
        }
        double distance = 0;
        for (int i = 0; i < mountainRange.length - 1; i++) {
            distance += Math.sqrt((Math.pow((mountainRange[i] - mountainRange[i + 1]), 2) + 1));
        }
        return distance;
    }


    /**
     * compute the longest (in terms of travel distance) seuqentially climbing part of a mountain range.
     *
     * @param mountainRange an array of doubles representing the heights of a mountain range. Will not be modified
     * @return the latest travel distance of any sequentially climbing segment of the mountain range
     */
    public static double longest_sequential_climb(double[] mountainRange) {
        if (mountainRange.length <= 1) {
            return 0;
        }
        double longest_climb_dist = 0;
        double local_climb_dist = 0;
        for (int i = 0; i < mountainRange.length - 1; i++) {
            if (mountainRange[i] < mountainRange[i + 1]) {
                local_climb_dist += Math.sqrt((Math.pow((mountainRange[i] - mountainRange[i + 1]), 2) + 1));
            } else if (local_climb_dist > longest_climb_dist) {
                longest_climb_dist = local_climb_dist;
            } else {
                local_climb_dist = 0;
            }
        }
        if (local_climb_dist > longest_climb_dist & longest_climb_dist == 0) {
            longest_climb_dist = local_climb_dist;
        }
        return longest_climb_dist;
    }

    /**
     * compute the numebr of peaks (higher than it's neighbors) and valleys (lower than it's neighbors) in a mountain
     *
     * @param mountainRange an array of doubles representing the heights of a mountain range. Will not be modified
     * @return a length 2 int array. position 1 is the number of peaks and position 2 the number of valleys.
     */
    public static int[] num_of_peaks_and_valleys(double[] mountainRange) {
        int numPeaks = 0;
        int numValleys = 0;

        int[] pair = new int[2];
        if (mountainRange.length == 1) {
            return pair;
        }
        for (int i = 1; i < mountainRange.length - 1; i++) {
            if (mountainRange[i] > mountainRange[i + 1] & mountainRange[i] > mountainRange[i - 1]) {
                numPeaks += 1;
            }
            if (mountainRange[i] < mountainRange[i + 1] & mountainRange[i] < mountainRange[i - 1]) {
                numValleys += 1;
            }
        }
        int[] points = new int[2];
        points[0] = numPeaks;
        points[1] = numValleys;
        return points;
    }

    /**
     * Creates a new array to indicate what would happen if we were to hypothetically fill the lowest parts of a
     * mountain range up to a fixed height
     *
     * @param mountainRange an array of doubles representing the heights of a mountain range. Will not be modified
     * @param target        a double. the new array will have all positions below this filled up to this height
     * @return a new array showing the result of filling in the array
     */
    public static double[] fill_valleys(double[] mountainRange, double target) {
        double[] arr = new double[mountainRange.length];
        for (int i = 0; i < mountainRange.length; i++) {
            if (mountainRange[i] < target) {
                arr[i] = target;
            } else {
                arr[i] = mountainRange[i];
            }
        }
        return arr;
    }
}