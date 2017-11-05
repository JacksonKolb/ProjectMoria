/*
CS 3810 - Database
Jackson Kolb and Max Morrison
HW5
DUE 10/26/2017 
*/

CREATE TABLE Payer (
	firstName VARCHAR(20),
	lastName VARCHAR(20),
	email VARCHAR(100) PRIMARY KEY
);

CREATE TABLE PayerAddress (
	city VARCHAR(30),
	state VARCHAR(30),
	country VARCHAR(30),
	areaCode INT,
	streetAddress VARCHAR(100),
	email VARCHAR(100) references Payer(email)
);

CREATE TABLE PayerPhoneNumbers(
	countryCode INT,
	localCode INT, 
	localNumber INT,
	email VARCHAR(100),
	FOREIGN KEY(email) references Payer(email)
);

CREATE TABLE Flights(
	uniqueFlightNumber INT PRIMARY KEY,
	lengthOfFlight INT,
	originCode INT,
	destCode INT,
	arrivalDate VARCHAR(50),
	arrivalHour INT,
	arrivalMin INT,
	departureDate VARCHAR(50),
	departureHour INT,
	departureMinute INT	
);

CREATE TABLE Booking( 
	numOfPassengers INT,
	bookingNumber INT PRIMARY KEY,
	bookingOriginID INT,
	bookingDate VARCHAR(50),
	payerFirstName VARCHAR(40),
	payerLastName VARCHAR(40),
	payerEmail VARCHAR(100) references Payer(email)
);

CREATE TABLE Passenger(
	payerEmail VARCHAR(100) references Payer(Email),
	bookingNumber INT references Booking(bookingNumber),
	uniqueFlightNumber INT references Flights(uniqueFlightNumber),
	passFirstName VARCHAR(50),
	passLastName VARCHAR(50),
	passEmail VARCHAR(100),
	passID INT PRIMARY KEY
);

CREATE TABLE PassengerAddress(
	country VARCHAR(25),
	city VARCHAR(25),
	state VARCHAR(25),
	postalCode INT,
	streetAddress VARCHAR(100),
	email VARCHAR(100) references Payer(email)
);


CREATE TABLE PassengerPhoneNumbers(
	countryCode INT,
	localCode INT, 
	localNumber INT,
	email VARCHAR(100),
	FOREIGN KEY(email) references Payer(email)
);

CREATE TABLE GoesOn(
	uniqueFlightNumber INT references Flights(uniqueFlightNumber),
	bookingNumber INT references Booking(bookingNumber),
	passID INT references Passenger(passID)
	
);