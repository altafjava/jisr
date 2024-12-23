package com.jisr.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jisr.entity.RoleEnum;

public class RoleDeserializer extends JsonDeserializer<RoleEnum> {
	
    @Override
	public RoleEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String roleValue = jsonParser.getText().toUpperCase();
        return RoleEnum.valueOf(roleValue);
    }
}
