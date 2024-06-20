USE test_db;
DROP PROCEDURE if exists [sp_DeleteTask]
GO  
CREATE PROCEDURE [sp_DeleteTask] 
@id int
AS   
BEGIN
Declare @Err_Msg AS varchar(50)

		BEGIN TRY
			BEGIN TRAN
				DELETE FROM Task
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

--execute [sp_DeleteTask] 1