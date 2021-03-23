package by.bsuir.converter;

public interface TwoWayConverter<S, T> {
    T convert(S source);
    S convertBack(T target);
}
