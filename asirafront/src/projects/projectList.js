import React, { useEffect, useState } from 'react';
// CSS
import './projects.css';
import Projects from './projects'
import axios from 'axios';

const ProjectList = () => {
    const endpointProjects = "http://localhost:8080/api/projects";
    var [data, setData] = useState([]);
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

