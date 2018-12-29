Software Requirements for this project include:
Android Studio, Android Phone, Lampp Server, PhpStorm (for writing php scripts in our case), Postman, Remmina Remote Desktop Client, PuTTY, Xming, Netbeans on Raspberry Pi, IP webcam App, VncServer, Virtual Box with Ubuntu.

An Android App named "49erSense" allows the login for 3 different type of users depending on their roles.

Customer is a vital part of the implementation as there exists smart devices like actuators/ sensors at his home for various electrical appliances. The data is stored in MySQl database via a php API. 


For Customer Live Feed, Weather forecast for 5 days is also implemented.

A raspberry pi has been used which periodically pulls data from the consumer server and simulates smart home appliances according to the status available on MySql db.

The raspberry pi simultaneously calculates the power used and sends the device sttus and power consumption units to the utlity and power generator servers.

Utility company can access the status of Customer and show data.

Power Generator fetches the data from the MySQL database of Customer and plots a realtime Barchart for the power consumption according to the status of Appliances.

Accomplishments:
Three seperate servers namely Consumer, Utility, Power generator on three different laptops.
