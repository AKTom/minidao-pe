package test;

import java.util.Date;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import examples.dao.EmployeeDao;
import examples.entity.Employee;

public class ClientDao {
	public static void main(String args[]) {
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeDao employeeDao = (EmployeeDao) factory.getBean("employeeDao");
		Employee employee = new Employee();
		employee.setId("8");
		employee.setBirthday(new Date());
		employee.setName("雇员张三");
		//调用minidao方法
		employeeDao.insert(employee);
	}
}
