package com.jisr.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequestDTO {
    private Map<String, Object> userUpdates;
    private Map<String, Object> patientProfileUpdates;
}
