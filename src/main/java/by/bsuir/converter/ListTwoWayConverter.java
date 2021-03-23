package by.bsuir.converter;

import java.util.List;

public interface ListTwoWayConverter<S, T>  {
    List<T> convertList(List<S> source);
    List<S> convertBackList(List<T> target);
}
