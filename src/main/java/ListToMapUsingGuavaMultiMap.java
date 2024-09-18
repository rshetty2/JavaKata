import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableListMultimap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListToMapUsingGuavaMultiMap {
    public static void main(String[] args) {

        List<Integer> arrivalTime = Arrays.asList(2,5,6,2,1);
        List<Integer> duration = Arrays.asList(1,2,1,3,1);

        ImmutableListMultimap.Builder<Integer, Integer> builder = ImmutableListMultimap.builder();
        for (int i = 0; i < Math.min(arrivalTime.size(), duration.size()); i++) {
            builder.put(arrivalTime.get(i), duration.get(i));
        }



        ImmutableListMultimap<Integer, Integer> multimap =
                builder.orderKeysBy((s1, s2) -> {
                    return s1.compareTo(s2);
                }).build();

        ImmutableCollection<Map.Entry<Integer,Integer>> coll =  multimap.entries();
        for (Map.Entry<Integer, Integer> integerIntegerEntry : coll) {
            System.out.println(integerIntegerEntry.getKey()+": "+integerIntegerEntry.getValue());
        }


//        Map<Integer, Integer> map = IntStream.range(0, arrivalTime.size()).boxed()
//                .collect(Collectors.toMap(i -> arrivalTime.get(i), i -> duration.get(i)));
//
//        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(map);
//        sortedMap.forEach((key, value) -> {
//            System.out.println("Key : " + key + " Value : " + value);
//        });
    }

}
