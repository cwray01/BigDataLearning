declare @level varchar(100)
declare @uid varchar(100)
declare cur cursor--定义一个游标
read_only
for select egg_code.user_id,egg_prize_level
from egg_code inner join egg_prize on egg_prize.user_id=egg_code.user_id--为所获得的数据集指定游标

open cur--打开游标
fetch next from cur into @uid,@level--把提取操作的列数据放到局部变量中
while(@@fetch_status=0)--返回被 FETCH 语句执行的最后游标的状态，而不是任何当前被连接打开的游标的状态。

begin
--print '等级：'+@level+'--------------用户ID：'+@uid

update egg_code set prize_level=@level   where user_id=@uid--执行操作

--提前下一位信息
fetch next from cur into @uid,@level
end
close cur--关闭游标
deallocate cur--删除游标
go