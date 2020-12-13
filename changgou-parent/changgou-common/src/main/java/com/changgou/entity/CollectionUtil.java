package com.changgou.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 * @author Jalen.Deng
 * @description 集合工具类
 * @date 2020/12/13 16:34
 **/
public final class CollectionUtil {
    public static <E> List<E> newArrayList() {
        return new ArrayList<>();
    }


    public static <E> List<E> newArrayList(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
    }


    public static <E> List<E> newArrayList(E element) {
        List<E> list = new ArrayList<>();
        list.add(element);
        return list;
    }


    @SafeVarargs
    public static <E> List<E> newArrayList(E... elements) {
        List<E> list = newArrayList(elements.length);
        for (E element : elements)
            list.add(element);
        return list;
    }


    @SafeVarargs
    public static <E> List<E> newNonNullArrayList(E... elements) {
        List<E> list = newArrayList(elements.length);
        for (E element : elements) {
            if (element != null)
                list.add(element);
        }
        return list;
    }


    public static <E> E firstElement(List<E> list) {
        return EmptyUtil.isEmpty(list) ? null : list.get(0);
    }


    public static <E> Set<E> newHashSet() {
        return new HashSet<>();
    }


    public static <E> Set<E> newHashSet(int initialCapacity) {
        return new HashSet<>(initialCapacity);
    }


    @SafeVarargs
    public static <E> Set<E> newHashSet(E... elements) {
        Set<E> set = newHashSet(elements.length);
        for (E element : elements)
            set.add(element);
        return set;
    }


    @SafeVarargs
    public static <E> Set<E> newNonNullHashSet(E... elements) {
        Set<E> set = newHashSet(elements.length);
        for (E element : elements) {
            if (element != null)
                set.add(element);
        }
        return set;
    }


    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<>();
    }


    public static <K, V> Map<K, V> newHashMap(int initialCapacity) {
        return new HashMap<>(initialCapacity);
    }

    public static int size(Collection<?> collection) {
        return (collection == null) ? 0 : collection.size();
    }

    @SafeVarargs
    public static <E> int size(Predicate<E> predicate, E... collection) {
        int count = 0;
        for (E element : collection) {
            if (predicate.test(element)) {
                count++;
            }
        }
        return count;
    }


    public static <E> void forEach(E[] list, Consumer<E> consumer) {
        if (EmptyUtil.isNotEmpty((Object[]) list)) {
            for (E e : list) {
                consumer.accept(e);
            }
        }
    }


    public static <E> void forEach(E[] list, BiConsumer<E, Integer> biConsumer) {
        if (EmptyUtil.isNotEmpty((Object[]) list)) {
            for (int i = 0; i < list.length; i++) {
                biConsumer.accept(list[i], Integer.valueOf(i));
            }
        }
    }

    public static <E> void forEach(Collection<E> collection, Consumer<E> consumer) {
        if (EmptyUtil.isNotEmpty(collection)) {
            for (E e : collection) {
                consumer.accept(e);
            }
        }
    }


    public static <E> void forEach(List<E> list, BiConsumer<E, Integer> biConsumer) {
        if (EmptyUtil.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                biConsumer.accept(list.get(i), Integer.valueOf(i));
            }
        }
    }

    public static <K, V> void forEach(Map<K, V> map, BiConsumer<K, V> biConsumer) {
        if (EmptyUtil.isNotEmpty(map)) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                biConsumer.accept(entry.getKey(), entry.getValue());
            }
        }
    }


    public static <K, V> Map<K, List<V>> groupBy(Collection<V> collection, Function<? super V, ? extends K> classifier) {
        if (EmptyUtil.isNotEmpty(collection)) {
            return (Map<K, List<V>>) collection.stream().collect(Collectors.toMap(classifier, CollectionUtil::newArrayList, (olds, news) -> {
                olds.addAll(news);
                return olds;
            }));
        }
        return newHashMap();
    }


    public static <K, V> Map<K, V> toMap(Collection<? extends V> collection, Function<? super V, ? extends K> keyMapper) {
        return toMap(collection, keyMapper, v -> v);
    }


    public static <E, K, V> Map<K, V> toMap(Collection<? extends E> collection, Function<? super E, ? extends K> keyMapper, Function<? super E, ? extends V> valueMapper) {
        Map<K, V> map = newHashMap();
        if (EmptyUtil.isNotEmpty(collection)) {
            for (E e : collection) {
                map.put(keyMapper.apply(e), valueMapper.apply(e));
            }
        }
        return map;
    }


    public static <E> List<E> filterToList(Collection<E> collection, Predicate<? super E> predicate) {
        if (EmptyUtil.isNotEmpty(collection)) {
            return (List<E>) collection.stream().filter(predicate).collect(Collectors.toList());
        }
        return newArrayList();
    }


    public static <E, R> List<R> mapToList(Collection<? extends E> collection, Function<? super E, ? extends R> mapper) {
        if (EmptyUtil.isNotEmpty(collection)) {
            return (List<R>) collection.stream().map(mapper).collect(Collectors.toList());
        }
        return newArrayList();
    }


    public static <E, R> List<R> mapNotNullToList(Collection<? extends E> collection, Function<? super E, ? extends R> mapper) {
        if (EmptyUtil.isNotEmpty(collection)) {
            return (List<R>) collection.stream().map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
        }
        return newArrayList();
    }


    public static <E, R> List<R> mapDistinctToList(Collection<? extends E> collection, Function<? super E, ? extends R> mapper) {
        if (EmptyUtil.isNotEmpty(collection)) {
            return (List<R>) collection.stream().map(mapper).distinct().collect(Collectors.toList());
        }
        return newArrayList();
    }


    public static <E> boolean anyMatch(Collection<E> collection, Predicate<? super E> predicate) {
        if (EmptyUtil.isNotEmpty(collection)) {
            return collection.stream().anyMatch(predicate);
        }
        return false;
    }


    public static <E> boolean noneMatch(Collection<E> collection, Predicate<? super E> predicate) {
        if (EmptyUtil.isNotEmpty(collection)) {
            return collection.stream().noneMatch(predicate);
        }
        return true;
    }


    public static <E> E best(Collection<? extends E> collection, BinaryOperator<E> compare) {
        if (EmptyUtil.isEmpty(collection)) {
            return null;
        }
        Iterator<? extends E> i = collection.iterator();
        E candidate = i.next();
        while (i.hasNext()) {
            candidate = compare.apply(candidate, i.next());
        }
        return candidate;
    }


    @SafeVarargs
    public static <E> boolean in(E target, E... collect) {
        if (EmptyUtil.isNotEmpty((Object[]) collect)) {
            Predicate<E> predicate = (target == null) ? (e -> (e == null)) : (e -> collect.equals(e));
            for (E e : collect) {
                if (predicate.test(e))
                    return true;
            }
        }
        return false;
    }


    public static <E> List<E> distinct(List<? extends E> list, Function<? super E, ?> keyMapper) {
        return distinct(list, keyMapper, null);
    }


    public static <E> List<E> distinct(List<? extends E> list, Function<? super E, ?> keyMapper, BinaryOperator<E> mergeMapper) {
        if (EmptyUtil.isNotEmpty(list)) {
            LinkedHashMap<? super Object, E> linkedHashMap = new LinkedHashMap<>((int) (list.size() * 0.75D));
            forEach(list, k -> {
                Object key = keyMapper.apply(k);
                E element = (E) linkedHashMap.get(key);
                if (element == null) {
                    linkedHashMap.put(key, k);
                } else if (mergeMapper != null) {
                    linkedHashMap.put(key, mergeMapper.apply(element, (E) k));
                }
            });
            return new ArrayList<>(linkedHashMap.values());
        }
        return newArrayList();
    }


    public static <E> Partition<E> partitionBy(Collection<? extends E> collection, Predicate<? super E> predicate) {
        List<E> first = new ArrayList<>();
        List<E> second = new ArrayList<>();
        if (EmptyUtil.isNotEmpty(collection)) {
            collection.stream().forEach(k -> {
                if (predicate.test(k)) {
                    first.add(k);
                } else {
                    second.add(k);
                }
            });
        }
        return new Partition<>(first, second);
    }


    public static <E> Partition<E> partitionBy(Collection<? extends E> collection, Predicate<? super E> firstPredicate, Predicate<? super E> secondPredicate) {
        List<E> first = new ArrayList<>();
        List<E> second = new ArrayList<>();
        if (EmptyUtil.isNotEmpty(collection)) {
            collection.stream().forEach(k -> {
                if (firstPredicate.test(k)) {
                    first.add(k);
                }
                if (secondPredicate.test(k)) {
                    second.add(k);
                }
            });
        }
        return new Partition<>(first, second);
    }

    public static class Partition<E> {
        private List<E> first;

        private List<E> second;


        public Partition(List<E> first, List<E> second) {
            this.first = first;
            this.second = second;
        }

        public List<E> getFirst() {
            return this.first;
        }

        public List<E> getSecond() {
            return this.second;
        }
    }
}
