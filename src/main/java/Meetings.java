public class Meetings {
//import java.io.*;
//import java.math.*;
//import java.security.*;
//import java.text.*;
//import java.util.*;
//import java.util.concurrent.*;
//import java.util.regex.*;
//
//
//
//    class Result {
//
//        /*
//         * Complete the 'maxEvents' function below.
//         *
//         * The function is expected to return an INTEGER.
//         * The function accepts following parameters:
//         *  1. INTEGER_ARRAY arrival
//         *  2. INTEGER_ARRAY duration
//         */
//
//        public static int maxEvents(List<Integer> arrival, List<Integer> duration) {
//            // Write your code here
//
//            if(arrival.size() == 1)
//                return 1;
//
//            int totalCounter = 1;
//            Integer occupiedEndTime = arrival.get(0) + duration.get(0);
//
//            for(int curr=1;curr<arrival.size();curr++) {
//
//                if(arrival.get(curr) >= occupiedEndTime || arrival.get(curr) + duration.get(curr) > occupiedEndTime) {
//                    totalCounter++;
//                    occupiedEndTime = arrival.get(curr) + duration.get(curr);
//                }
//
//            }
//            return totalCounter;
//        }
//
//    }
//
//    public class Solution {
//        public static void main(String[] args) throws IOException {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//            int arrivalCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//            List<Integer> arrival = new ArrayList<>();
//
//            for (int i = 0; i < arrivalCount; i++) {
//                int arrivalItem = Integer.parseInt(bufferedReader.readLine().trim());
//                arrival.add(arrivalItem);
//            }
//
//            int durationCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//            List<Integer> duration = new ArrayList<>();
//
//            for (int i = 0; i < durationCount; i++) {
//                int durationItem = Integer.parseInt(bufferedReader.readLine().trim());
//                duration.add(durationItem);
//            }
//
//            int result = Result.maxEvents(arrival, duration);
//
//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
//
//            bufferedReader.close();
//            bufferedWriter.close();
//        }
//    }

}
