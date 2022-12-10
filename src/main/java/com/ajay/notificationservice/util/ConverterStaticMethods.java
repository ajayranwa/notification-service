package com.ajay.notificationservice.util;


import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;

public class ConverterStaticMethods {
    private static final Logger logger = LoggerUtil.getLogger(ConverterStaticMethods.class);

    public static String convertJsonNodeToString(JsonNode jsonNode) {
        logger.info("Inside convertJsonNodeToString");
        logger.debug("jsonNode:  " + jsonNode);

        if (jsonNode == null) {
            logger.warn("jsonNode input is null, returning null");
            return null;
        }

        return jsonNode.toPrettyString();
    }

}