import './App.css';
import React from 'react';
import SignIn from './signIn/signIn';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Register from './register/register';
import ProjectList from './projects/projectList';
import Error from './error';

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
          <Route exact path ="/projects">
            <ProjectList />
          </Route>
          <Route exact path ="/error">
            <Error />
          </Route>
        </Switch>
      </BrowserRouter>
  );
}

export default App;
