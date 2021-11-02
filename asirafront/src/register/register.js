import React, { useState } from 'react';
import './register.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { AiOutlineLock } from 'react-icons/ai';
import axios from "axios";
import { useHistory } from "react-router-dom";

const Register = () => {
    const [managedVm, setManagedVm] = useState({});
    let history = useHistory();
    var error = false;
    const endpointRegister = "http://localhost:8080/api/register";
  
    const handleChange = (event) => {
        error = false;
        const name = event.target.name;
        const value = event.target.value;
        setManagedVm(values => ({...values, [name]: value}))
      }
    
    const handleSubmit = (event) => {
      if(managedVm.password !== managedVm.confirmPassword){
          error = true;
      }
      var data = {
        "login": managedVm.name,
        "email": managedVm.email,
        "password": managedVm.password,
        "langKey":"en"
      }
      axios.post(endpointRegister, data).then(res => {
        if(res.status === 201) {
           goLogin(); 
        } else {
            error = true;
        }
      });
      event.preventDefault();
    }

    const goLogin = () => {
      history.push("/")
    }
    
    return (
        <>
        <section className="login-dark">
            <form onSubmit={handleSubmit}>
                <h2 className="visually-hidden">Login Form</h2>
                <div className="illustration"><AiOutlineLock color={'#089CC8 '} height="250px" width="250px" /></div>
                { error === true ? <span>Error in password</span> : null}
                <div className="mb-3"><input type="text" className="form-control" placeholder="username" name="name" value={managedVm.name || ''} onChange={handleChange} /></div>
                <div className="mb-3"><input type="text" className="form-control" placeholder="email" name="email" value={managedVm.email || ''} onChange={handleChange} /></div>
                <div className="mb-3"><input type="password" className="form-control" placeholder="Password" name="password" value={managedVm.password || ''} onChange={handleChange}/></div>
                <div className="mb-3"><input type="password" className="form-control" placeholder="Confirm Password" name="confirmPassword" value={managedVm.confirmPassword || ''} onChange={handleChange}/></div>
                <div className="mb-3"><button  className="btn btn-primary d-block w-100" type="submit">Log In</button></div>
                <a className="forgot" href="/">Already have an account?</a>
            </form>
        </section>
    </> 
    );
}

export default Register;