package com.bridgelabz.addressbookapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.ToString;
public class AddressDTO {
    @NotEmpty(message = "Name Should not be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = " Name Should be Valid")
    public String name;
    @NotEmpty(message = "Address Should not be empty")
    public String address;
    @NotEmpty(message = "City Should not be Empty")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "City Should be Valid")
    public String city;
    @NotEmpty(message = "State should not be empty")
    @Pattern(regexp = "^[a-zA-Z]+\\s[a-zA-Z]+$",message = "State Should be valid")
    public String state;
    @NotEmpty(message = "Zip Code should not be empty")
    @Pattern(regexp = "\\d{6}" ,message = "Zip Code should be valid")
    public String zipcode;

}
