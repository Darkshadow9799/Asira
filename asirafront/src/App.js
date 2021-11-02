import './App.css';
import React from 'react';
import SignIn from './signIn/signIn';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Register from './register/register';

function App() {
  return (
      <BrowserRouter>
        <Switch>
          <Route exact path ="/">
            <SignIn />
          </Route>
          <Route exact path="/register">
            <Register />
          </Route>
        </Switch>
      </BrowserRouter>
  );
}

export default App;
