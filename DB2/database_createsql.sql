-- Create the database
CREATE DATABASE codecademy;
GO

-- Use the created database
USE codecademy;
GO

-- Create table for student
CREATE TABLE student (
    emailAddress VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    birthdate DATE,
    gender VARCHAR(10),
    address VARCHAR(255),
    city VARCHAR(100),
    country VARCHAR(100)
);
GO

-- Create table for course
CREATE TABLE course (
    courseID INT PRIMARY KEY IDENTITY,
    courseName VARCHAR(255),
    subject VARCHAR(255),
    introductionText VARCHAR(255),
    level VARCHAR(20)
);
GO

-- Create table for module
CREATE TABLE module (
    moduleID INT PRIMARY KEY IDENTITY,
    title VARCHAR(255),
    version VARCHAR(50),
    description VARCHAR(255),
    contactPersonName VARCHAR(255),
    contactPersonEmail VARCHAR(255),
    courseID INT FOREIGN KEY REFERENCES course(courseID),
    CONSTRAINT UQ_Module UNIQUE (title, version)
);
GO

-- Create table for webcast
CREATE TABLE webcast (
    webcastID INT PRIMARY KEY IDENTITY,
    title VARCHAR(255),
    description TEXT,
    speakerName VARCHAR(255),
    speakerOrganization VARCHAR(255),
    duration TIME,
    publicationDate DATE,
    url VARCHAR(255)
);
GO

-- Create table for enrollment
CREATE TABLE enrollment (
    enrollmentID INT PRIMARY KEY IDENTITY,
    studentEmailAddress VARCHAR(255) FOREIGN KEY REFERENCES student(emailAddress),
    courseID INT FOREIGN KEY REFERENCES course(courseID),
    enrollmentDate DATE
);
GO

-- Create table for progress
CREATE TABLE progress (
    progressID INT PRIMARY KEY IDENTITY,
    enrollmentID INT FOREIGN KEY REFERENCES enrollment(enrollmentID),
    contentItemID INT FOREIGN KEY REFERENCES module(moduleID) ON DELETE CASCADE,
    percentageWatched DECIMAL(5,2)
);
GO
