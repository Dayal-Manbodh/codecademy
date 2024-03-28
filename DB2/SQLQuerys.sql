
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