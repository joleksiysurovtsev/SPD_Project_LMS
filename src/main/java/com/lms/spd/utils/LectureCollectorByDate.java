package com.lms.spd.utils;

import com.lms.spd.models.interfaces.Lecture;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class LectureCollectorByDate implements Collector<Lecture, Map<Boolean, List<Lecture>>, Map<Boolean, List<Lecture>>> {

    private Calendar currentDate;

    public LectureCollectorByDate(Calendar currentDate) {
        this.currentDate = currentDate;
    }

    public static LectureCollectorByDate collectToSortedMapByDate(Calendar currentDate) {
        return new LectureCollectorByDate(currentDate);
    }

    /**
     * method returns a Supplier instance that generates an empty accumulator instance,
     * so, in this case, we can simply write:
     **/
    @Override
    public Supplier<Map<Boolean, List<Lecture>>> supplier() {
        return HashMap::new;
    }

    /**
     * method returns a function that is used for adding a new element to an existing accumulator object
     */
    public BiConsumer<Map<Boolean, List<Lecture>>, Lecture> accumulator() {
        return (map, s) -> {
            List<Lecture> lectureList = new ArrayList<>();
            if (map.containsKey(s.getLectureDate().after(currentDate))) {
                lectureList = map.get(s.getLectureDate().after(currentDate));
                lectureList.add(s);
            } else {
                lectureList.add(s);
            }
            map.put(s.getLectureDate().after(currentDate), lectureList);
        };
    }

    /**
     * The combiner() method returns a function that is used for merging two accumulators together:
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Lecture>>> combiner() {
        return (map1, map2) -> {
            map2.forEach((k, v) -> map1.merge(k, v, (v1, v2) -> {
                v1.addAll(v2);
                return v1;
            }));
            return map1;
        };
    }

    /**
     * method returns a function that is used for converting an accumulator to final result type.
     */
    @Override
    public Function<Map<Boolean, List<Lecture>>, Map<Boolean, List<Lecture>>> finisher() {
        return map -> {
            map.forEach((key, value) -> value.sort(Comparator.comparing(Lecture::getLectureDate)));
            return map;
        };
    }

    /**
     * method is used to provide Stream with some additional information that will be used for internal optimizations.
     */
    @Override
    public Set<Collector.Characteristics> characteristics() {
        Set<Characteristics> characteristics = new HashSet<>();
        characteristics.add(Characteristics.CONCURRENT);
        return characteristics;
    }
}
