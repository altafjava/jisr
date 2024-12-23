package com.jisr.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileTemplateDTO {
    private int profileCompletionPercentage;
    private Map<String, Object> userFields;
    private Map<String, Object> patientProfileFields;
    private List<String> unfilledFields;
}
