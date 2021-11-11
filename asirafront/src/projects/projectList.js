import React from 'react';
// CSS
import './projects.css';
import Projects from './projects'
import axios from 'axios';

const ProjectList = () => {
    var data;
    data = [
      {
        "projName": "Asira",
        "orgId": "1",
        "projAdminId": "1"
      },
      {
        "projName": "Asira",
        "orgId": "1",
        "projAdminId": "1"
      },
      {
        "projName": "Asira",
        "orgId": "1",
        "projAdminId": "1"
      },
      {
        "projName": "Asira",
        "orgId": "1",
        "projAdminId": "1"
      }
    ]
  return (
    <section className='projectList'>
    {data.map((book, index) => {
      return <Projects {...book}></Projects>;
    })}
  </section>
  );
}

export default ProjectList;

