@echo OFF
echo Creating component
set /p name=Enter component name: 
echo Creating component with name: %name%
cd asirafront/src
md %name%
cd %name%
echo import React from 'react';> %name%.js
echo .body{}> %name%.css
pause