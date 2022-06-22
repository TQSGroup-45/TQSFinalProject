import { ClientRegister } from './../profile/clientRegister';
import { Client } from './../profile/client';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  checkoutForm : FormGroup = this.formBuilder.group({
    name: '',
    email: '',
    dob: '',
    sname: '',
    snum:'',
    city: '',
    pc1: '',
    pc2: ''
  });

  info!:ClientRegister;


  constructor( private formBuilder: FormBuilder, private http: HttpClient ) {  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    console.warn('Registration submitted', this.checkoutForm.value);
    
    var formData: any = new FormData();
    formData.append('name', this.checkoutForm.get('name')!.value);
    formData.append('email', this.checkoutForm.get('email')!.value);
    formData.append('dob', this.checkoutForm.get('dob')!.value);
    formData.append('sname', this.checkoutForm.get('sname')!.value);
    formData.append('snum', this.checkoutForm.get('snum')!.value);
    formData.append('city', this.checkoutForm.get('city')!.value);
    formData.append('pc1', Number(this.checkoutForm.get('pc1')!.value));
    formData.append('pc2', this.checkoutForm.get('pc2')!.value);

    this.info = { name:this.checkoutForm.get('name')!.value, 
                  dob:this.checkoutForm.get('dob')!.value,
                  email:this.checkoutForm.get('email')!.value,
                  sname:this.checkoutForm.get('sname')!.value,
                  snum:this.checkoutForm.get('snum')!.value,
                  pc1:this.checkoutForm.get('pc1')!.value,
                  pc2:this.checkoutForm.get('pc2')!.value,
                  city:this.checkoutForm.get('city')!.value};

    const options = {headers: {'Content-Type': 'application/json'}};
    this.http
      .post<ClientRegister>("http://localhost:8080/api/v1/clients/", this.info, options)
      .subscribe({
        next: (response) => console.log(response),
        error: (error) => console.log(error),
      });


    this.checkoutForm.reset();
  }
}
