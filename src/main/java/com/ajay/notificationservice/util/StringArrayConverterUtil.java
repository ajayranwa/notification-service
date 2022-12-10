package com.ajay.notificationservice.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

import static com.ajay.notificationservice.util.ConverterStaticMethods.convertJsonNodeToString;

@Converter
public class StringArrayConverterUtil implements AttributeConverter<List<String>, String> {
    private static final Logger logger = LoggerUtil.getLogger(StringArrayConverterUtil.class);

    @Override
    public String convertToDatabaseColumn(List<String> stringArray) {
        logger.info("Inside convertToDatabaseColumn");

        if (stringArray == null) {
            logger.warn("stringArray input is null, returning null");
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.valueToTree(stringArray);
        String res = convertJsonNodeToString(jsonNode);
        System.out.println("\n" + res + "\n");
        return res;
    }

    @Override
    public List<String> convertToEntityAttribute(String arrayString) {
        logger.info("Inside convertToEntityAttribute");
        logger.debug("arrayString:  " + arrayString);

        if (arrayString == null || arrayString.isEmpty()) {
            logger.warn("arrayString input is empty, returning null");
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(arrayString, new TypeReference<List<String>>() {
            });
        } catch (JsonProcessingException e) {
            logger.error("Error parsing jsonNodeString", e);
        }
        return null;
    }

}