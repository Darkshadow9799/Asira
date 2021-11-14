import React, { useEffect, useState } from 'react';
import './projects.css';
import Projects from './projects'
import axios from 'axios';
import { urls } from '../constants';
import { useHistory } from 'react-router';

const ProjectList = () => {
    var [data, setData] = useState([]);
    let history = useHistory();
    const endpointProjects = urls.urlPrefix + urls.projectList;
    // Data for testing Projects component without database or backend running
    // data = [{"projName":"Asira","orgId":"1","projAdminId":"1"},{"projName":"Asira","orgId":"1","projAdminId":"1"},{"projName":"Asira","orgId":"1","projAdminId":"1"},{"projName":"Asira","orgId":"1","projAdminId":"1"}]
    useEffect(()=>{
      if(localStorage.getItem("authorization") === null || localStorage.getItem("authorization") === undefined){
        history.push("/");
      }
      axios.get(endpointProjects).then(res => {
        console.log(res);
        if(res.status === 200){
          setData(res.data);
        } else {
          history.push("/error");
        }
      });
  }, [])
    
  return (
    <section className='projectList'>
    {data.length > 0 ?data.map((project, index) => {
      return <Projects key={project.id} {...project}></Projects>;
    }) : <h1>No projects</h1>}
  </section>
  );
}

export default ProjectList;

