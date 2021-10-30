import React, { useState } from 'react';
import './signIn.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { AiOutlineLock } from 'react-icons/ai';
import axios from "axios";

const SignIn = () => {
      const [name, setName] = useState();
      const [password, setPassword] = useState();
      const endpoint = "http://localhost:8080/api/authenticate";
    
      const handleChangeName= (event) => {
        setName(event.target.value);
      }

      const handleChangePassword= (event) => {
        setPassword(event.target.value);
      }
    
      const handleSubmit = (event) => {
        console.log(password);
        var data = {
          "username": name,
          "password": password,
          "rememberMe":false
        }
        axios.post(endpoint, data).then(res => {
          // console.log(res);
          localStorage.setItem("authorization", res.data.id_token);
        });
        event.preventDefault();
      }
    
    return (
        <>
        <section className="login-dark">
            <form onSubmit={handleSubmit}>
                <h2 className="visually-hidden">Login Form</h2>
                <div className="illustration"><AiOutlineLock color={'#089CC8 '} height="250px" width="250px" /></div>
                <div className="mb-3"><input type="text" className="form-control" placeholder="email" value={name || ''} onChange={handleChangeName} /></div>
                <div className="mb-3"><input type="password" className="form-control" placeholder="Password" value={password || ''} onChange={handleChangePassword}/></div>
                <div className="mb-3"><button  className="btn btn-primary d-block w-100" type="submit">Log In</button></div>
            </form>
        </section>
    </> 
    );
}

export default SignIn;