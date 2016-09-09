MiniDao-PE (超轻量级JAVA持久层框架)
=======
当前最新版本： 1.6.1 （发布日期：20160912）


###MiniDao-PE 简介及特征

MiniDao-PE 是一款超级轻量的JAVA持久层框架，基于SpringJdbc 实现，具备Mybatis一样的标签和SQL灵活性。可无缝集成Hibernate项目，支持事务统一管理，有效解决Hibernate项目，实现灵活的SQL分离问题。 

具有以下特征:

* 1. O/R mapping不用设置xml，零配置便于维护
* 2. 不需要了解JDBC的知识
* 3. SQL语句和java代码的分离
* 4. 只需接口定义，无需接口实现
* 5. SQL支持脚本语言（强大脚本语言）
* 6. 支持与hibernate轻量级无缝集成
* 7. 支持自动事务处理和手动事务处理





### 接口定义[EmployeeDao.java]  
    @MiniDao
    public interface EmployeeDao {
	
     public List<Map> getAllEmployees(@Param("employee") Employee employee);
    
     Employee getEmployee(@Param("empno") String empno);
    
     Map getMap(@Param("empno")String empno,@Param("name")String name);

     @Sql("SELECT count(*) FROM employee")
     Integer getCount();

     int update(@Param("employee") Employee employee);

     void insert(@Param("employee") Employee employee);
   }
    
    
    
### SQL文件[EmployeeDao_getAllEmployees.sql]
    SELECT * FROM employee where 1=1 
    <#if employee.age ?exists>
	and age = :employee.age
    </#if>
    <#if employee.name ?exists>
	and name = :employee.name
    </#if>
    <#if employee.empno ?exists>
	and empno = :employee.empno
    </#if>

###接口和SQL文件对应目录
![github](http://www.jeecg.org/data/attachment/forum/201308/18/224051ey14ehqe000iegja.jpg "minidao")

	
### MiniDao在spring中配置
    <!-- MiniDao动态代理类 -->
	<bean id="miniDaoHandler" class="org.jeecgframework.minidao.factory.MiniDaoBeanScannerConfigurer">
		<!-- 是使用什么字母做关键字Map的关键字 默认值origin 即和sql保持一致,lower小写(推荐),upper 大写 -->
		<property name="keyType" value="lower"></property>
		<!-- 格式化sql -->
		<property name="formatSql" value="false"></property>
		<!-- 输出sql -->
		<property name="showSql" value="false"></property>
		<!-- 数据库类型 -->
		<property name="dbType" value="mysql"></property>
		<!-- dao地址,配置符合spring方式 -->
		<property name="basePackage" value="examples.dao.*"></property>
		<!-- 使用的注解,默认是Minidao,推荐 Repository-->
		<property name="annotation" value="org.springframework.stereotype.Repository"></property>
		<!-- Minidao拦截器配置 
		<property name="emptyInterceptor" ref="minidaoInterceptor"></property>
		-->
	</bean>

### 测试代码
    public class Client {
    public static void main(String args[]) {
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
     		
		EmployeeDao employeeDao = (EmployeeDao) factory.getBean("employeeDao");
		Employee employee = new Employee();
		List<Map> list =  employeeDao.getAllEmployees(employee);
		for(Map mp:list){
			System.out.println(mp.get("id"));
			System.out.println(mp.get("name"));
			System.out.println(mp.get("empno"));
			System.out.println(mp.get("age"));
			System.out.println(mp.get("birthday"));
			System.out.println(mp.get("salary"));
		}
	}
    }


技术交流
-----------------------------------
* 作 者：  张代浩
* 论 坛： [www.jeecg.org](http://www.jeecg.org)
* 邮 箱：  jeecg@sina.com
* QQ交流群：325978980、143858350
