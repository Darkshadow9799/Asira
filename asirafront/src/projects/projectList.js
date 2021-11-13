import React, { useEffect, useState } from 'react';
import './projects.css';
import Projects from './projects'
import axios from 'axios';
import { urls } from '../constants';

const ProjectList = () => {
    var [data, setData] = useState([]);
    const endpointProjects = urls.urlPrefix + urls.projectList;
    // Data for testing Projects component without database or backend running
    // data = [{"projName":"Asira","orgId":"1","projAdminId":"1"},{"projName":"Asira","orgId":"1","projAdminId":"1"},{"projName":"Asira","orgId":"1","projAdminId":"1"},{"projName":"Asira","orgId":"1","projAdminId":"1"}]
    useEffect(()=>{
      axios.get(endpointProjects).then(res => {
        console.log(res);
        setData(res.data);
      });
  }, [])
    
  return (
    <section className='projectList'>
    {data.map((project, index) => {
      return <Projects key={project.id} {...project}></Projects>;
    })}
  </section>
  );
}

export default ProjectList;

