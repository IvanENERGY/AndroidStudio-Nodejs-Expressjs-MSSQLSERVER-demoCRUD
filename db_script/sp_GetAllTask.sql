USE test_db;
DROP PROCEDURE if exists [sp_GetAllTask]
GO  
CREATE PROCEDURE [sp_GetAllTask]

AS   
BEGIN

SELECT id
,taskName
,deadline
,reps
,filePath
FROM Task

END
GO  
--execute [sp_GetAllTask]