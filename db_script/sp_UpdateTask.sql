USE test_db;
DROP PROCEDURE if exists [sp_UpdateTask]
GO  
CREATE PROCEDURE [sp_UpdateTask] 
	@id int,
	@taskName nvarchar(100),
	@deadline datetime,
	@reps int,
	@filePath nvarchar(100)
AS   
BEGIN
Declare @Err_Msg AS varchar(50)

		BEGIN TRY
			BEGIN TRAN
				UPDATE dbo.Task
				SET taskName= @taskName,
					deadline=@deadline,
					reps=@reps,
					filePath=@filePath 
				WHERE id=@id
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
--execute [sp_UpdateTask] 1,"fyp2", "2023-06-13" ,12,"somepic.png"