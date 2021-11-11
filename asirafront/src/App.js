import './App.css';
import React from 'react';
import SignIn from './signIn/signIn';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Register from './register/register';
import ProjectList from './projects/projectList';

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
        </Switch>
      </BrowserRouter>
  );
}

export default App;
