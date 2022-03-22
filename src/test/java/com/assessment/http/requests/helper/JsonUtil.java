package com.assessment.http.requests.helper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class JsonUtil {

    public static <T> T jsonToDto(String filename, Class<T> type) throws IOException {
        InputStream inputStream = type.getClassLoader().getResourceAsStream(filename);
        return new ObjectMapper().registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
                .readValue(inputStream, type);
    }

    public static <T> List<T> jsonToListDto(String path, Class<T[]> entity) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibility(
                VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return Arrays.asList(objectMapper.readValue(new FileReader(new ClassPathResource(path).getFile()), entity));
    }

}
