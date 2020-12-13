package com.changgou.entity;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Jalen.Deng
 * @description 对象判空工具类-java8
 * @date 2020/12/13 16:45
 **/
public class OptionalUtil {
    public static <T> Optional<T> of(T value) {
        return Optional.of(value);
    }

    public static <T> void ifPresent(T value, Consumer<? super T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }

    }

    public static void ifNotEmpty(String value, Consumer<String> consumer) {
        if (EmptyUtil.isNotEmpty(value)) {
            consumer.accept(value);
        }

    }

    public static <T> void ifNotEmpty(Collection<T> value, Consumer<Collection<T>> consumer) {
        if (EmptyUtil.isNotEmpty(value)) {
            consumer.accept(value);
        }

    }

    public static <T, U> U map(T value, Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        return value != null ? mapper.apply(value) : null;
    }

    public static <T> T orElse(T value, T other) {
        return value != null ? value : other;
    }

    public static <T> T orElseGet(T value, Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    private OptionalUtil() {
    }
}
