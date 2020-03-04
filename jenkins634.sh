source ~/.bash_rc
cd /home/devrabbit/SmartQE/SmartQE-Users/7/Selenium/test_notifii_demo
curl -d @config634.xml -H 'Content-Type: text/xml' 'http://devrabbit:rabbit@192.168.1.141:8080/createItem?name=test_notifii_demo' --user devrabbit:114de69b453efd3524fce8756b1ed0e69c
curl -X POST http://devrabbit:rabbit@192.168.1.141:8080/job/test_notifii_demo/config.xml --user devrabbit:114de69b453efd3524fce8756b1ed0e69c --data-binary @config634.xml
curl -X POST http://devrabbit:rabbit@192.168.1.141:8080/job/test_notifii_demo/build --user devrabbit:114de69b453efd3524fce8756b1ed0e69c
