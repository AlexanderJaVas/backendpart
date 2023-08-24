ABOUT

This is a back end part of web application "OpenAI API Chat" which provides:

- users authorization and registration,
- API for front end,
- requests to and responses from OpenAI API,
- interaction with MongoDB server.

It is written on Java with Spring framework.

This application is running on Internet and is available on URL https://staring-blindly-into.space

HOW TO USE.

1. Clone this repository in IDE
2. Due to security reasons OpenAI API key and DB critentials can't be saved on Github, so you have to create new file .env in main/resources and add it to .gitignore file.
   The .env file content:

       MONGO_DATABASE="mongoDB name here"
       MONGO_USER=""
       MONGO_PASSWORD=""
       MONGO_CLUSTER=""
       OPENAI_API_KEY=""
       SSL_KEY_STORE=""
       SSL_PASSWORD=""
   
 
4. Run the application in IDE
5. Also you can make .jar file with command mvn deploy and copy it on remote server and then run it with java -jar your_filename.jar command. Ensure that TCP ports 443 and 27017 are not blocked by any firewall
