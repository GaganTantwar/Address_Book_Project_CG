package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressDTO;
import com.bridgelabz.addressbookapp.exception.AddressBookException;
import com.bridgelabz.addressbookapp.model.AddressBookData;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressBookAppService implements InterfaceAddressBookAppService {
    @Autowired
    AddressBookRepository addressBookRepository;
    // List to store address book data
    List<AddressBookData> addressBookList = new ArrayList<>();

    // Method to get all address book data
    @Override
    public List<AddressBookData> getAddressBookData() {
        return addressBookRepository.findAll();
    }

    // Method to get address book data by phone number
    @Override
    public AddressBookData getAddressBookDataByPhone(long phone) {
        return addressBookRepository.findById(phone).orElseThrow(()-> new AddressBookException("Person Not Found"));
    }
    @Override
    public List<AddressBookData> getAddressByCity(String city){
        return addressBookRepository.findByCity(city);
    }

    // Method to create new address book data
    @Override
    public AddressBookData createAddressBookData(long phone, AddressDTO data) {
        AddressBookData addData = null;
        addData = new AddressBookData(phone, data); // Create new address book data
        return addressBookRepository.save(addData);
    }

    // Method to update existing address book data
    @Override
    public AddressBookData updateAddressBookData(long phone, AddressDTO data) {
        AddressBookData updateData = this.getAddressBookDataByPhone(phone); // Find existing data by phone number
         updateData.updateAddressBookData(phone,data);// Replace the existing data with updated data
        return addressBookRepository.save(updateData);
    }

    // Method to delete address book data by phone number
    @Override
    public void deleteAddressBookData(long phone) {
        AddressBookData addData=this.getAddressBookDataByPhone(phone);
        addressBookRepository.delete(addData);
    }
}
