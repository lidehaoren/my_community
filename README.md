##我的博客

##资料
[Spring导航](https://spring.io/guides)

[Spring WEB](https://spring.io/guides/gs/serving-web-content/)

[Github-OAuth](https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app)

[maven仓库](https://mvnrepository.com/artifact/com.h2database/h2/1.4.200)
##开发工具
[Git](https://git-scm.com/)

##前端使用bootstrap快速搭建
[bootstrap](https://v3.bootcss.com/)

##使用了okhttp来发送post请求
[okhttp](https://square.github.io/okhttp/)

##使用了H2作为数据库
[H2](http://h2database.com/html/main.html)

##脚本
```sql
create table USER
(
	ID INT auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFY BIGINT,
	constraint USER_PK
		primary key (ID)
);
```
