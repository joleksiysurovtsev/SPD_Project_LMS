package com.lms.spd;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class LectureCollector  {
//    //   Student, Map<String, List<Student>>, List<StudentResponse>
//    @Override
//    public Supplier<Map<LectureType, List<Lecture>>> supplier() {
//        return () -> new ConcurrentHashMap<>();
//    }
//
//    @Override
//    public BiConsumer<Map<LectureType, List<Lecture>>, Lecture> accumulator() {
//        return (store, lecture) -> store.merge(lecture.getType(),
//                new ArrayList<>(Arrays.asList(lecture)), combineLists());
//    }
//
//    @Override
//    public BinaryOperator<Map<LectureType, List<Lecture>>> combiner() {
//        return (x, y) -> {
//            x.forEach((k, v) -> y.merge(k, v, combineLists()));
//            return y;
//        };
//    }
//
////    @Override
////    public Function<Map<String, List<Student>>, List<StudentResponse>> finisher() {
////        return (store) -> store
////                .keySet()
////                .stream()
////                .map(course -> new StudentResponse(course, store.get(course)))
////                .collect(Collectors.toList());
////    }
//
//    Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper,
//                             Function<? super T, ? extends U> valueMapper,
//                             BinaryOperator<U> mergeFunction)
//
//    @Override
//    public Function<Map<LectureType, List<Lecture>>, Map<LectureType, List<Lecture>>> finisher() {
//        return  Lecture.stream().collect(Collectors.toMap(Lecture::getType, Lecture::getName));
//        //return (store) -> store.keySet().stream().map(LectureType )//.collect(Collectors.toMap(LectureType,List<Lecture>));
////                .keySet()
//       // return (store)-> store.keySet().stream().map(type -> new HashMap<LectureType, List<Lecture>>());
//    }
//
//    @Override
//    public Set<Characteristics> characteristics() {
//        return null;
//    }
//
//
//    private <T> BiFunction<List<T>, List<T>, List<T>> combineLists() {
//        return (lecture, lectures2) -> {
//            lectures2.addAll(lecture);
//            return lectures2;
//        };
//    }

}
