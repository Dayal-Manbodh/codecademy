-- Testdata voor student
INSERT INTO student (emailAddress, name, birthdate, gender, address, city, country)
VALUES
('john@example.com', 'John Doe', '1990-05-15', 'Male', '123 Main Street', 'Springfield', 'USA'),
('jane@example.com', 'Jane Smith', '1992-08-20', 'Female', '456 Elm Street', 'Riverside', 'USA'),
('mark@example.com', 'Mark Johnson', '1988-03-10', 'Male', '789 Oak Street', 'Lakeview', 'Canada');

-- Testdata voor course
INSERT INTO course (courseName, subject, introductionText, level)
VALUES
('Introduction to Programming', 'Computer Science', 'This course introduces the basics of programming.', 'Beginner'),
('Data Structures and Algorithms', 'Computer Science', 'This course covers advanced topics in data structures and algorithms.', 'Advanced'),
('Web Development Fundamentals', 'Web Development', 'This course provides an introduction to web development technologies.', 'Beginner');

-- Testdata voor module
INSERT INTO module (title, version, description, contactPersonName, contactPersonEmail, courseID)
VALUES
('Introduction to Java', '1.0', 'This module covers the basics of Java programming language.', 'John Smith', 'john.smith@example.com', 1),
('Sorting Algorithms', '2.0', 'This module covers various sorting algorithms.', 'Jane Doe', 'jane.doe@example.com', 2),
('HTML and CSS Basics', '1.0', 'This module provides an introduction to HTML and CSS.', 'Emily Johnson', 'emily.johnson@example.com', 3);

-- Testdata voor webcast
INSERT INTO webcast (title, description, speakerName, speakerOrganization, duration, publicationDate, url)
VALUES
('Introduction to Java Programming', 'A webinar introducing Java programming language.', 'John Smith', 'Codecademy', '01:30:00', '2024-03-01', 'https://example.com/java-webinar'),
('Introduction to Data Structures', 'A webinar covering basic data structures.', 'Jane Doe', 'Codecademy', '01:45:00', '2024-03-05', 'https://example.com/data-structures-webinar'),
('Responsive Web Design', 'A webinar on responsive web design techniques.', 'Emily Johnson', 'Codecademy', '01:15:00', '2024-03-10', 'https://example.com/responsive-web-design-webinar');

-- Testdata voor enrollment
INSERT INTO enrollment (studentEmailAddress, courseID, enrollmentDate)
VALUES
('john@example.com', 1, '2024-03-01'),
('jane@example.com', 2, '2024-03-05'),
('mark@example.com', 3, '2024-03-10');

-- Testdata voor progress
INSERT INTO progress (enrollmentID, contentItemID, percentageWatched)
VALUES
(1, 1, 50),
(2, 2, 75),
(3, 3, 30);
