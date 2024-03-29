
-- dit is query voor overzicht 2 om de gemmidelde percentage per cursus te vinden 

SELECT 
	moduleID,
    title AS ModuleTitle,
    AVG(percentageWatched) AS AverageProgressPercentage
FROM 
    module
INNER JOIN 
    progress ON moduleID = contentItemID
WHERE 
    courseID = '1'
	GROUP BY 
    moduleID,title;

-- dit is query voor overzicht 3 om de gemmidelde percentage per module te vinden 

SELECT 
    moduleID,
    title AS ModuleTitle,
    percentageWatched AS ProgressPercentage
FROM 
    module
LEFT JOIN 
    progress ON moduleID = contentItemID
AND
    enrollmentID = '1'
WHERE 
    courseID = '1';


-- dit is de query voor overzicht 5 om te bereken welke 3 webcasten het meest bekeken zijn. 

SELECT TOP 3
    webcastID,
    title,
    SUM(percentageWatched * DATEDIFF(minute, '00:00:00', duration)) AS totalWatchTime_minutes
FROM
    webcast
JOIN
    progress ON webcastID = contentItemID
GROUP BY
    webcastID,
    title
ORDER BY
    totalWatchTime_minutes DESC;

    -- student aanmaken

INSERT INTO student (emailAddress, name, birthdate, gender, address, city, country) VALUES (?, ?, ?, ?, ?, ?, ?)

-- cursus aanmaken

INSERT INTO course (courseName, subject, introductionText, level) VALUES (?, ?, ?, ?)

-- module aanmaken

INSERT INTO module (title, version, description, contactPersonName, contactPersonEmail, courseID) VALUES (?, ?, ?, ?, ?, ?)

-- webcast aanmaken

INSERT INTO webcast (title, description, speakerName, speakerOrganization, duration, publicationDate, url) VALUES (?, ?, ?, ?, ?, ?, ?)

-- inschrijving aanmaken

INSERT INTO enrollment (studentEmailAddress, courseID, enrollmentDate) VALUES (?, ?, ?)

-- progress aanmaken

INSERT INTO progress (enrollmentID, contentItemID, percentageWatched) VALUES (?, ?, ?)

-- alle studenten ophalen

SELECT * FROM student

-- alle cursussen ophalen

SELECT * FROM course

-- alle modules ophalen

SELECT * FROM module

-- alle webcasts ophalen

SELECT * FROM webcast

-- alle inschrijvingen ophalen

SELECT * FROM enrollment

-- alle progress ophalen

SELECT * FROM progress

-- studenten updaten

UPDATE student SET name = ?, birthdate = ?, gender = ?, address = ?, city = ?, country = ? WHERE emailAddress = ?

-- cursussen updaten

UPDATE course SET courseName = ?, subject = ?, introductionText = ?, level = ? WHERE courseID = ?

-- modules updaten

UPDATE module SET title = ?, version = ?, description = ?, contactPersonName = ?, contactPersonEmail = ?, courseID = ? WHERE moduleID = ?

-- webcasts updaten

UPDATE webcast SET title = ?, description = ?, speakerName = ?, speakerOrganization = ?, duration = ?, publicationDate = ?, url = ? WHERE webcastID = ?

-- inschrijvingen updaten

UPDATE enrollment SET studentEmailAddress = ?, courseID = ?, enrollmentDate = ? WHERE enrollmentID = ?

-- progress updaten

UPDATE progress SET enrollmentID = ?, contentItemID = ?, percentageWatched = ? WHERE progressID = ?