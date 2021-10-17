@echo OFF
echo Starting push
for /f %%i in ('git branch --show-current') do set VARIABLE=%%i
echo Pushing to %VARIABLE% branch
set /p comment=Enter comment: 
echo %comment%
set /p sure=Are you sure? (Y/N)
if %sure% == y (
git add .
git commit -m "%comment%"
git push
)
if %sure% == Y (
git add .
git commit -m "%comment%"
git push
) 
if %sure% == n (
echo Not pushed
)
if %sure% == N (
echo Not pushed
)
pause