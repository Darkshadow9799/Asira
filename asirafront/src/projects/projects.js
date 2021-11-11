import React from 'react'

const Projects = (props) => {
 console.log("Projects");
 return (
  <article
   className='project'
   onMouseOver={() => {
    console.log(props.projName);
   }}
  >
   <h3>Project Name: {props.projName}</h3>
   <h4 onClick={() => console.log(props.orgId)}>Project org: {props.orgId}</h4>
   <h4>Project admin: {props.projAdminId}</h4>
  </article>
 );
};

export default Projects;