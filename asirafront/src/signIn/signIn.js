import React, { useState } from 'react';
import './signIn.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { AiOutlineLock } from 'react-icons/ai';
import axios from "axios";
import { useHistory } from "react-router-dom";
import { urls } from '../constants';

const SignIn = () => {
    let history = useHistory();
    const [name, setName] = useState();
    const [password, setPassword] = useState();
    const endpointAuthenticate = urls.urlPrefix + urls.authentication;
    
    const handleChangeName= (event) => {
      setName(event.target.value);
    }

    const handleChangePassword= (event) => {
      setPassword(event.target.value);
    }
    
    const handleSubmit = (event) => {
      var data = {
        "username": name,
        "password": password,
        "rememberMe":false
      }
      axios.post(endpointAuthenticate, data).then(res => {
        console.log(res);
        if(res.status === 200){
          localStorage.setItem("authorization", res.data.id_token);
          getProject();
        } else {
          console.log(res.statusText);
          history.push("/");
        }
      });
      event.preventDefault();
    }

    const getProject = () => {
      // axios.get(endpointProjects).then(res => {
      //   console.log(res);
        history.push({
          pathname: '/projects',
        });
      //   <ProjectList {...res.data}></ProjectList>
      // });
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
                <a className="forgot" href="/register">Forgot your email or password?</a>
            </form>
        </section>
    </> 
    );
}

export default SignIn;