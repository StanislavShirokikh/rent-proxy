package org.example.rentproxy.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mapper extends ModelMapper {
    Configuration configuration;

    public Mapper() {
        this.configuration = getConfiguration().setSkipNullEnabled(true);
    }

    public <S, T> Set<T> mapSet(Set<S> sourceSet, Class<T> targetClass) {
        return sourceSet.stream()
                .map(source -> this.map(source, targetClass))
                .collect(Collectors.toSet());
    }

    public <S, T> List<T> convertToList(List<S> sourceList, Function<S, T> mapper) {
        return sourceList.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
