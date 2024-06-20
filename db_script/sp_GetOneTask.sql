USE test_db;
DROP PROCEDURE if exists [sp_GetOneTask]
GO  
CREATE PROCEDURE [sp_GetOneTask]
@id int
AS   
BEGIN

SELECT id
,taskName
,deadline
,reps
,filePath
FROM Task
WHERE id=@id

END
GO  
--execute [sp_GetOneTask] 1