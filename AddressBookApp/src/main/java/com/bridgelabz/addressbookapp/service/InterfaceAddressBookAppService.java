package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressDTO;
import com.bridgelabz.addressbookapp.model.AddressBookData;

import java.util.*;

// Interface for Address Book App Service
public interface InterfaceAddressBookAppService {

    // Method to get all address book data
    List<AddressBookData> getAddressBookData();

    // Method to get address book data by phone number
    AddressBookData getAddressBookDataByPhone(long phone);
    List<AddressBookData> getAddressByCity(String city);
    // Method to create new address book data
    AddressBookData createAddressBookData(long phone, AddressDTO addressDTO);

    // Method to update existing address book data
    AddressBookData updateAddressBookData(long phone, AddressDTO addressDTO);

    // Method to delete address book data by phone number
    void deleteAddressBookData(long phone);
}
