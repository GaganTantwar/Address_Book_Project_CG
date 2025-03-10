package com.bridgelabz.addressbookapp.repository;

import com.bridgelabz.addressbookapp.model.AddressBookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;
public interface AddressBookRepository extends JpaRepository<AddressBookData,Long> {
    @Query(value="select *from address_book where city=:city",nativeQuery = true)
    List<AddressBookData>findByCity(String city);
}
