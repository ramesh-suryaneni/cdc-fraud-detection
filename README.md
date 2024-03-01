# Application

Imagine a financial services company that processes transactions. Each transaction needs to be analyzed for fraud detection in real-time.

## scenario

assume 3 transactions happened in less duration
1. transaction1: user used credit card for 20000 in Hyderabad
2. transaction2: user used credit card for 300000 in Banglore
3. transaction3: user used credit card for 40000 in Delhi

basically 3 transactions within 1 hr in different locations not possible, some fraud is happening and we need to alert user and block his account further transactions not to happen.

## pre-requites
* Docker installed
* clone this repo to local

### How to?
run docker containers
```docker  compose up -d```
wait for all conatiners to running state(5 min, initial db schama created)
* kafka1 - apache kafka container as streaming engine(we can run as cluster also)
* kafka-ui - for testing kafka brokers, topics etc(http://localhost:8181)
* db - mysql database
* adminer - a db UI client(http://localhost:8282; user:user, password:pass, host:db)
* kafka-mysql-connector-debezium - connector between mysql and kafka(http://localhost:8083)
### register connector(manual activity)
after all containers running, listen db modification events(insert,update,delete) in kafka topics. 

For more details about connector:  https://debezium.io/documentation/reference/stable/connectors/index.html

connect mysql to kafka, using psotman execute REST api call

metthod = POST

endpoint = http://localhost:8083/connectors

request body = json (copy json from here https://github.com/ramesh-suryaneni/cdc-fraud-detection/blob/main/database/connector-config.json)

to test connector other apis

GET http://localhost:8083/connectors (lists all connectors)

DELETE http://localhost:8083/connectors/replace-your-connector-name

### run kafka stream processor apps
* processor1 - import spring boot project into your favorite IDE and run (https://github.com/ramesh-suryaneni/cdc-fraud-detection/tree/main/processor1)
* processor2 - another spring boot project to run(https://github.com/ramesh-suryaneni/cdc-fraud-detection/tree/main/processor2)

#### note: both java projects should be running to see results, verify kafka topics using kafka-ui

## test data
use DB client to insert below records

```
INSERT INTO user(user_id, name, acccount_locked) VALUES("100", "abc", "N");
INSERT INTO user(user_id, name, acccount_locked) VALUES("101", "xyz", "N");

INSERT INTO transactions(user_id, amount, tx_type, location) VALUES ("100", "10000", "2", "Hyderabad");
INSERT INTO transactions(user_id, amount, tx_type, location) VALUES ("100", "20000", "2", "Delhi");
INSERT INTO transactions(user_id, amount, tx_type, location) VALUES ("100", "30000", "2", "Bangalore");

INSERT INTO transactions(user_id, amount, tx_type, location) VALUES ("101", "10000", "2", "Hyderabad");
INSERT INTO transactions(user_id, amount, tx_type, location) VALUES ("101", "20000", "2", "Delhi");
INSERT INTO transactions(user_id, amount, tx_type, location) VALUES ("101", "30000", "2", "Bangalore");
```



