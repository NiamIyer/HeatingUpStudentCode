import java.util.ArrayList;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Niam
 */

public class WeatherPatterns {


    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {
        // Array for dynamic programming
        int[] stepsTo = new int[temperatures.length];
        // Adjacency list
        ArrayList<Integer>[] connections = new ArrayList[temperatures.length];
        int max = 1;
        // Initialize and populate the adjacency list
        for (int i = 0; i < connections.length; i++) {
            connections[i] = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (temperatures[j] < temperatures[i]) {
                    connections[i].add(j);
                }
            }
        }
        // Sets the first temperature as 1 run length
        stepsTo[0] = 1;
        // Calls recursive method for each temperature
        for (int i = 0; i < connections.length; i++) {
            max = Math.max(findLongest(stepsTo, i, connections), max);
        }

        return max;
    }
    // Recursive method
    public static int findLongest(int[] stepsTo, int index, ArrayList<Integer>[] connections) {
        int len = 1;
        // Checks to see if the past temperatures have a calculated run length
        for (int i = 0; i < connections[index].size(); i++) {
            if (stepsTo[connections[index].get(i)] > 0) {
                len = Math.max(len, stepsTo[connections[index].get(i)] + 1);
            }
            else {
                // If not, calls the same method on past temperature
                len = Math.max(len, findLongest(stepsTo, connections[index].get(i), connections) + 1);
            }
        }
        // Stores calculated run length
        stepsTo[index] = len;
        return len;
    }
}
