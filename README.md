##How to deploy the Offer Totals application

- From the root of the project run: `docker-compose up`
This will start 2 Postgres databases and the frontend application
- Then start the backend application: `java -jar ./target/offer-1.0.0.jar`
NOTE: the java version should 11
- In a browser go to http://localhost:9000
- Sign with user name 'user' and password '123userabc'