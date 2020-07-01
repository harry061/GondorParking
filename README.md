# GondorParking

Application is a  multi tenant, multi level parking system designed to resolve the parking problems of Gondor. 
This system can be extended to handle different vehicles and can update the no of floors or the no of serviceable lanes or the no of the slots that can be reserved as  well.

## Data Load
* Data has been loaded through a script.
* Data can be queried using H2 using the below url
```
http://localhost:{port}/gondorparking/h2-console/
```

## URLs

* Parking Summary can be requested by the below url:
```
http://localhost:8080/gondorparking/pworker/retrieveparkingsummary/{parkingSystemId}/{buildingId}

http://localhost:{port}/gondorparking/pworker/retrieveparkingsummary/1/1
```

* Reserving a parking slot:
```
http://localhost:8080/gondorparking/pworker/reserveslot
```

* Input is a parking request in Json :
```JSON
{"vehicleNumber":"KA01AP7904","vehicleType":"CAR","parkingSystemId":1,"parkingType": "ELDER"}
```
```JSON
{"vehicleNumber":"KA01AP7904","vehicleType":"BIKE","parkingSystemId":1,"parkingType": "REGULAR"}
```
* System outputs a Parking token:
```JSON
{
  "txnId": 1,
  "parkingSystemId": 1,
  "buildingId": 1,
  "floorId": 1,
  "laneId": 1,
  "slotId": 1,
  "parkingType": "ELDER",
  "parkingStartTime": "2019-09-11T08:35:37.956+0000"
}
```
* Vacating a parking slot
* Return the parking token to vacate the parking slot.
```JSON
{
  "txnId": 1,
  "parkingType": "ELDER"
}
```
On exit a parking receipt is returned
```JSON 
{
  "parkedTime": "2019-09-11T12:20:09.681+0000",
  "exitTime": "2019-09-11T12:20:18.183+0000"
}
```

if the token is not valid, error message with error code is returned.
```JSON
{
  "errorCode": "P002",
  "errorDescription": "Invalid parking token."
}
```
