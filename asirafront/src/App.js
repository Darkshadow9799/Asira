import logo from './logo.svg';
import './App.css';
import React from 'react';
import SignIn from './signIn/signIn';
import axios from 'axios';

function App() {
  var jwtToken;

  axios.interceptors.request.use(
    function(config) {
      jwtToken = localStorage.getItem("authorization");
      if (jwtToken) {
        config.headers["Authorization"] = "Bearer " + jwtToken;
      }
      return config;
    },
    function(err) {
      return Promise.reject(err);
    }
  );

  return (
    <React.Fragment>
      <header></header>
      <SignIn ></SignIn>
      <footer></footer>
    </React.Fragment>
  );
}

export default App;
