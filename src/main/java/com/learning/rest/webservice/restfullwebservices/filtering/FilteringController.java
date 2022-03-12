package com.learning.rest.webservice.restfullwebservices.filtering;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {


    private MappingJacksonValue CustomFilter(String field1, String field2, String filterId, Object targetBean){
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(field1, field2);

        FilterProvider filters = new SimpleFilterProvider().addFilter(filterId, filter);

        MappingJacksonValue mapping = new MappingJacksonValue(targetBean);
        mapping.setFilters(filters);

        return mapping;
    }


    // respond with only value1, value2
    @GetMapping("/filtering")
    public MappingJacksonValue retrievesSomeBean(){
        SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
        MappingJacksonValue filterResult = CustomFilter("field1", "field2", "SomeBeanFilter", someBean);

        return filterResult;
    }

    // respond with only value2, value3
    @GetMapping("/filtering/list")
    public MappingJacksonValue retrievesListOfSomeBean(){
        List<SomeBean> list = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"),
                new SomeBean("Value12", "Value22", "Value32"));
        MappingJacksonValue filterResult = CustomFilter("field2", "field3", "SomeBeanFilter", list);


        return filterResult;
    }

}
