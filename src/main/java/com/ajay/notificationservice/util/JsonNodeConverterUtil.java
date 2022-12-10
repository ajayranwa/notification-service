package com.ajay.notificationservice.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.ajay.notificationservice.util.ConverterStaticMethods.convertJsonNodeToString;

@Converter
public class JsonNodeConverterUtil implements AttributeConverter<JsonNode, String> {
    private static final Logger logger = LoggerUtil.getLogger(JsonNodeConverterUtil.class);

    @Override
    public String convertToDatabaseColumn(JsonNode jsonNode) {
        logger.info("Inside convertToDatabaseColumn");
        return convertJsonNodeToString(jsonNode);
    }

    @Override
    public JsonNode convertToEntityAttribute(String jsonNodeString) {
        logger.info("Inside convertToEntityAttribute");
        logger.debug("jsonNodeString:  " + jsonNodeString);

        if (jsonNodeString == null || jsonNodeString.isEmpty()) {
            logger.warn("jsonNodeString input is empty, returning null");
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(jsonNodeString);
        } catch (JsonProcessingException e) {
            logger.error("Error parsing jsonNodeString", e);
        }
        return null;
    }

}