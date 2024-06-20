create table Task(
id int identity(1,1),
taskName nvarchar(100),
deadline datetime,
reps int,
filePath nvarchar(100)
)