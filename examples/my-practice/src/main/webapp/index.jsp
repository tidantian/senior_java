<%@page import="org.springframework.context.*"%>
<%@page import="org.springframework.web.context.support.*"%> 
<%@page import="javax.persistence.*"%> 
<%@page import="com.framework.io.jpa.spring.*"%> 

<html>
<body>
	<h2>Hello World!!</h2>

	<%
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		EntityManagerFactory emf = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
		TaskDaoImpl taskDaoImpl = (TaskDaoImpl) ctx.getBean("taskDaoImpl");
		JpaTest jpaTest = (JpaTest) ctx.getBean("jpaTest");
	%>
	<%=emf%>
	<%=taskDaoImpl%>
	<%=jpaTest.test()%>

</body>
</html>
