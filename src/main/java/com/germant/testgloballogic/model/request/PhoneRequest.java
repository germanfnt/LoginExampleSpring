package com.germant.testgloballogic.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequest {

    private long id;
    private long number;
    private int cityCode;
    private String countryCode;
}
