package com.jisr.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jisr.model.Role;

public class RoleDeserializer extends JsonDeserializer<Role> {
	
    @Override
    public Role deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String roleValue = p.getText().toUpperCase();
        return Role.valueOf(roleValue);
    }
}
