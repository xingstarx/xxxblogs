--{call copy_record_byId(#{p_id,mode=IN},#{p_record_table,mode=IN},#{r_succuss_flag,mode=OUT,jdbcType=VARCHAR})}
drop procedure copy_record_byId;
call copy_record_byId(1,'tb_article',@p_out);
show create procedure copy_record_byId;
show procedure status;

-------------------------
-------------------------
----文章日志的存储过程-----
-------------------------
delimiter //
create procedure copy_record_byId(in p_id int,in p_record_table varchar(20),out r_succuss_flag varchar(10))
begin
declare sql_oper_revcord varchar(256);
DECLARE EXIT HANDLER FOR SQLEXCEPTION set r_succuss_flag='N';
set @sql_oper_revcord = concat('insert into ',p_record_table,'_l  select * from ',p_record_table,' where id=',p_id);
PREPARE sql_oper_revcord FROM @sql_oper_revcord;
EXECUTE sql_oper_revcord;
set r_succuss_flag='Y';
end;
//
delimiter ;