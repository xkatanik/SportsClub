package cz.muni.fi.pa165.sportsclub.mapping;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * Created by norbert on 5.11.16.
 */
public interface BeanMappingService {

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
