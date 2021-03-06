//package com.radixdigit.junitText;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//
//import com.radixdigit.entity.UserType;
//
///*@ContextConfiguration(locations = {
//		"WebRoot/WEB-INF/classes/com/radixdigit/config/applicationContext-.xml",
//		"WebRoot/WEB-INF/classes/com/radixdigit/config/applicationContext-WebServlet.xml" })*/
//public class TextCRRD {
//	private SqlSessionFactory ssmSessionFactory;
//	private static ApplicationContext context;
//	private static String local = "WebRoot/WEB-INF/classes/com/radixdigit/config/applicationContext-.xml";
//	static {
//		context = new FileSystemXmlApplicationContext(local);
//	}
//
//	@Before
//	public void initMethod() throws IOException {
//		ssmSessionFactory = (SqlSessionFactory) context
//				.getBean("ssmSessionFactory");
//	}
//
//	/**
//	 * 添加User
//	 */
//	@Test
//	public void addUser() {
//		System.out.println(ssmSessionFactory);
//		SqlSession session = null;
//		try {
//			session = ssmSessionFactory.openSession();
//			System.out.println("session" + session);
//
//			UserType u = new UserType();
//			u.settId(00000000000000000000000000000000000);
//
//			int i = session.insert(
//					"com.radixdigit.mapper.UserTypeMapper.insert", u);
//			System.out.println(i);
//			session.commit();
//		} catch (Exception e) {
//			session.rollback();
//			e.printStackTrace();
//		} finally {
//			if (session != null)
//				session.close();
//		}
//	}
//
//	/**
//	 * 更新User
//	 */
//	public void updateUser() {
//		SqlSession session = null;
//		try {
//			session = ssmSessionFactory.openSession();
//			UserType u = session.selectOne("findUserById", 4);
//			System.out.println(u);
//			u.setTypeName("赵信");
//			session.update(
//					"com.radixdigit.mapper.UserTypeMapper.updateByPrimaryKey",
//					u);
//			session.commit();
//		} catch (Exception e) {
//			session.rollback();
//			e.printStackTrace();
//		} finally {
//			if (session != null)
//				session.close();
//		}
//	}
//
//	/**
//	 * 模糊查询
//	 */
//
//	public void likeQuery() {
//		SqlSession session = null;
//		String name = "皇";
//		try {
//			session = ssmSessionFactory.openSession();
//			Map map = new HashMap();
//			map.put("name", "皇");
//			List<UserType> listUser = session.selectList(
//					"com.radixdigit.mapper.UserTypeMapper.likeQuery", map);
//			System.out.println(listUser.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (session != null)
//				session.close();
//		}
//	}
//
//	/**
//	 * 删除User
//	 */
//
//	public void deleteUser() {
//		SqlSession session = null;
//		try {
//			session = ssmSessionFactory.openSession();
//			session.delete(
//					"com.radixdigit.mapper.UserTypeMapper.deleteByPrimaryKey",
//					1);
//			session.commit();
//		} catch (Exception e) {
//			session.rollback();
//			e.printStackTrace();
//		} finally {
//			if (session != null)
//				session.close();
//		}
//	}
//
//}
