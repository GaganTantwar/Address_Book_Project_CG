import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'AddressApp';
  list: any[] = [];

  constructor() {}

  ngOnInit() {
    this.fetchData();
  }
  

  async fetchData() {
    try {
      const response = await fetch(`http://localhost:8080/address`);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const responseData = await response.json();
  
      // Extract data from ResponseDTO
      if (responseData.data && Array.isArray(responseData.data)) {
        this.list = responseData.data;
      } else {
        this.list = [];
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  }
  // post data method

  async sendPostRequest(nameP: string, addressP: string, cityP: string, stateP: string, zipcodeP: string, phoneX: bigint) {
    const postData = { name: nameP, address: addressP, city: cityP, state: stateP, zipcode: zipcodeP };
    let phone = phoneX;
  
    try {
      const response = await fetch(`http://localhost:8080/address/create/${phone}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(postData)
      });
  
      const responseData = await response.json();
      console.log('Success:', responseData);
      this.fetchData(); // Refresh data after adding a record
    } catch (error) {
      console.error('Error:', error);
    }
  }
   


 async add() {
    let name = prompt("Enter Name") || "";
    let address = prompt("Enter Address") || "";
    let city=prompt("Enter city")||"";
    let state=prompt("Enter State")||"";
    let zipcode=prompt("Enter Zip Code")||"";
    let phone=prompt("Enter Phone Number")||"";
    let phonep = BigInt(phone);

    if (name && address && city && state && zipcode && phone ) {
      this.sendPostRequest(name, address,city,state,zipcode,phonep);
      setTimeout(() => {
        window.location.reload();
      }, 2000);
    } else {
      console.warn("All fields are required!");
    }
  }


  // delete a record
  async delete() {
    let phone = prompt("Enter Phone Number to delete") || "";
    if (!phone) {
      console.warn("Phone number is required!");
      return;
    }
  
    try {
      const response = await fetch(`http://localhost:8080/address/delete/${phone}`, {
        method: 'DELETE'
      });
  
      if (!response.ok) {
        throw new Error('Failed to delete record');
      }
  
      const responseData = await response.json();
      console.log('Record deleted successfully:', responseData);
      this.fetchData(); // Refresh after deletion
    } catch (error) {
      console.error('Error:', error);
    }
  }
  
}