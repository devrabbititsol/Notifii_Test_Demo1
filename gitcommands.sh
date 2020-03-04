cd /home/devrabbit/SmartQE/SmartQE-Users/7/Selenium/test_notifii_demo
cd /home/devrabbit/SmartQE/SmartQE-Users/7/Selenium/test_notifii_demo
git init
 git add .
git rm -r --cached target
git rm -r --cached test-output
git rm -r --cached gitcommands.bat
git rm -r --cached gitcommands.sh
git rm -r --cached APIReports
git rm -r --cached MobileReports
git rm -r --cached WebReports
git rm -r --cached jenkins*
git rm -r --cached config*
git rm -r --cached DockerFiles
git rm -r --cached screenshots
git rm -r --cached copyDockerFiles.bat
git rm -r --cached copyDockerFiles.sh
git commit -m "testing in server22"
git remote add origin https://23f2056ff1d6a113aaf7ae557dc93c811994c697@github.com/devrabbititsol/Notifii_Test_Demo1.git
git push -u origin master

