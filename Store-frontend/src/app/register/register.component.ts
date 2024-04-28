import { Component, OnInit, inject } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { Client } from '../models/client';
import { ClientService } from '../service/client/client.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  formGr?:FormGroup|any;
  passwordVisible: boolean = false;
  confirmPasswordVisible: boolean = false; 

  private clientService = inject(ClientService);

  constructor(private formB:FormBuilder){
    this.formGr = formB.group({
     name: ['', Validators.required],
     lastName:['',Validators.required],
     email: ['',[Validators.required,Validators.email]],
     address: ['', Validators.required],
     gender: ['', Validators.required],
     password: ['',[Validators.required,this.validatorRegex(),Validators.minLength(8)]],
     passwordS: ['',Validators.required],
    },
    {
      validators: this.matchValidator('password', 'passwordS')
    })
  };

  ngOnInit(): void {
    
    
  }
  sendInformation() {
    console.log(this.formGr.value);
    console.log(this.formGr.valid);
    
    if (this.formGr.valid) {
      const { passwordS, ...clientData } = this.formGr.value;
      this.clientService.createClient(clientData).subscribe({
        next: (response: any) => {
          alert(`Welcome! Client created successfully.`);
        },
        error: (error: any) => {
          console.log(error);
        }
      });
    }
  }
  matchValidator(controlName: string, matchingControlName: string): ValidatorFn {
    return (abstractControl: AbstractControl) => {
        const control = abstractControl.get(controlName);
        const matchingControl = abstractControl.get(matchingControlName);

        if (matchingControl!.errors && !matchingControl!.errors?.['passwordS']) {
            return null;
        }

        if (control!.value !== matchingControl!.value) {
          const error = { passwordS: 'Passwords do not match.' };
          matchingControl!.setErrors(error);
          return error;
        } else {
          matchingControl!.setErrors(null);
          return null;
        }
    }
  }
  
  validatorRegex():ValidatorFn{
    return (control)=>{
      const password = control.value as string;
      const regex = /^(?=.*[a-z])(?=.*[A-Z])/; 
      if(!regex.test(password)){
        return { 'invalidPass': true };
      }
      return null;
    }
  }
  togglePasswordVisibility() {
    this.passwordVisible = !this.passwordVisible;
  }
  toggleConfirmPasswordVisibility() {
    this.confirmPasswordVisible = !this.confirmPasswordVisible;
  }

  hasErrors(controlName:string,typeError:string){
    return this.formGr.get(controlName)?.hasError(typeError) && this.formGr.get(controlName)?.touched
  }
}
