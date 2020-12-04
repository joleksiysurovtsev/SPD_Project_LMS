package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

public class LectureCollector implements Collector<Lecture, List<Lecture>, Map<LectureType, List<Lecture>>> {

    /**
     * method returns a Supplier instance that generates an empty accumulator instance,
     * so, in this case, we can simply write:
     **/
    @Override
    public Supplier<List<Lecture>> supplier() {
        return ArrayList::new;
    }

    /**
     * method returns a function that is used for adding a new element to an existing accumulator object
     */
    @Override
    public BiConsumer<List<Lecture>, Lecture> accumulator() {
        return null;
    }

    /**
     * The combiner() method returns a function that is used for merging two accumulators together:
     */
    @Override
    public BinaryOperator<List<Lecture>> combiner() {
        return null;
    }

    /**
     * method returns a function that is used for converting an accumulator to final result type.
     */
    @Override //finisher
    public Function<List<Lecture>, Map<LectureType, List<Lecture>>> finisher() {
        return null;
    }

    /**
     * method is used to provide Stream with some additional information that will be used for internal optimizations.
     */
    @Override //характеристики
    public Set<Collector.Characteristics> characteristics() {
        return null;
    }


}
