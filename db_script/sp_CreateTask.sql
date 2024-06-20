USE test_db;
DROP PROCEDURE if exists [sp_CreateTask]
GO  
CREATE PROCEDURE [sp_CreateTask] 
	@taskName nvarchar(100),
	@deadline datetime,
	@reps int,
	@filePath nvarchar(100)
AS   
BEGIN
Declare @Err_Msg AS varchar(50)

		BEGIN TRY
			BEGIN TRAN
				Insert into Task (taskName,deadline,reps,filePath)
				values(@taskName,@deadline,@reps,@filePath )
			COMMIT TRAN
		END TRY

		BEGIN CATCH
			SET @Err_Msg=ERROR_MESSAGE()
			ROLLBACK TRAN
		END CATCH	

	IF (@Err_Msg is null)	
		SELECT 'SUCCESS' AS tranState
	ELSE
		SELECT @Err_Msg AS tranState

END
GO
--execute [sp_CreateTask] "fyp","2023-06-12" ,3,"some.png"