package untils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * jdbcUntil
 * @author 李林林
 * @date 2017年7月10日 上午11:23:20
 */
public class DButil {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private static String url1 = "jdbc:mysql://localhost:3306/ceshi?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8";
	private static String url2 = "jdbc:mysql://localhost:3306/ceshi2?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8";

	/**
	 * 获取连接
	 * @return
	 */
	public Connection getConnection(int choose){
		String url = choose == 1 ? url1 : url2;
		System.out.println(url);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,"root","root");
		}catch (Exception e) {
			System.out.println("DBUtils.getConnection---获取连接失败！");
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 执行查询操作
	 * @param sql
	 * @return 返回值是一个结果集
	 */
	public ResultSet executeQuery(String sql,int choose){
		try {
			conn = this.getConnection(choose);
			stmt = conn.createStatement();
			rs =  stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("DBUtils.executeQuery---执行查询操作失败！");
			e.printStackTrace();
		}
		finally {
			close();
		}
		return rs;
	}
	public int updateQuery(String sql,int choose){
		int count= 0;
		try {
			conn = this.getConnection(choose);
			ps = conn.prepareStatement(sql);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DBUtils.executeQuery---执行更新操作失败！");
			e.printStackTrace();
		}
		finally {
			close();
		}
		return count;
	}
	/**
	 * 执行带参数的查询操作
	 * @param sql sql语句
	 * @param obj Object数组
	 * @return
	 */
	public ResultSet executeQuery(String sql, Object[] obj,int choose){
		try {
			conn = this.getConnection(choose);
			ps = conn.prepareStatement(sql);
			preparStateSql(obj, obj.length);
			rs =  ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("DBUtils.executeQuery---执行查询操作失败！");
			e.printStackTrace();
		}
		finally {
			close();
		}
		return rs;
	}

	/**
	 * 执行更新、插入、删除等操作
	 * @param sql
	 * @param obj
	 * @return 返回值是操作影响的行数(即更新、插入或删除了几条数据)
	 */
	public int executeUpdate(String sql, Object[] obj,int choose){
		int count = 0;
		try{
			conn = this.getConnection(choose);
			ps = conn.prepareStatement(sql);
			preparStateSql(obj, obj.length);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DBUtils.executeUpdate---执行更新、插入、删除等操作失败！");
			e.printStackTrace();
		} finally {
			close();
		}
		return count;
	}

	/**
	 * 装载PreparedStatement
	 * @param obj
	 * @param length
	 * @throws SQLException
	 */
	private void preparStateSql(Object[] obj, int length) throws SQLException {
		for(int i=0;i<length;i++)
		{
			if(obj[i]==null){
				ps.setObject(i+1, null);
			}else if(obj[i].getClass()==String.class){
				ps.setString(i+1, obj[i].toString());
			}else if(obj[i].getClass()==Integer.class){
				ps.setInt(i+1, (Integer)obj[i]);
			}else if(obj[i].getClass()==Double.class){
				ps.setDouble(i+1, (Double)obj[i]);
			}else if(obj[i].getClass()==Date.class){	//java.sql.Date
				ps.setDate(i+1, (Date)obj[i]);
			}else if(obj[i].getClass()==java.util.Date.class){	//java.util.Date
				ps.setDate(i+1, new Date(((java.util.Date)obj[i]).getTime()));
			}else if(obj[i].getClass()==ByteArrayInputStream.class){
				ps.setBinaryStream(i+1, (ByteArrayInputStream)obj[i]);
			}else if(obj[i].getClass()==FileInputStream.class){
				ps.setBinaryStream(i+1, (FileInputStream)obj[i]);
			}else{
				ps.setObject(i+1, obj[i]);
			}
		}
	}

	/**
	 * 关闭数据库连接
	 */
	public void close(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		DButil db = new DButil();
		String sql1 = "select * from user ";
		String sql2 = "select * from info ";
		try {
			Context context = new InitialContext();
			//			UserTransaction userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
			//			userTransaction.setTransactionTimeout(600);
			//			userTransaction.begin();
			//			Connection conn1 = db.getConnection(1);
			//			Connection conn2 = db.getConnection(2);
			//			System.out.println("----------个对象成功就位！"); 
			//			PreparedStatement ps1 = conn1.prepareStatement(sql1);
			//			PreparedStatement ps2 = conn2.prepareStatement(sql2);
			//String str = "xgxw";
			//	ps.setString(1, str);
			//			ResultSet rs1 =  ps1.executeQuery();
			//			ResultSet rs2 =  ps2.executeQuery();
			////			userTransaction.commit();
			//			while(rs1.next()){
			//				System.out.println(rs1.getString(2));
			//				System.out.println("It is Ok!");
			//			}
			//
			//			while(rs2.next()){
			//				System.out.println(rs2.getString(3));
			//				System.out.println("It is Ok!");
			//			}
		} catch (Exception e) {
			System.out.println("DBUtils.executeQuery---执行查询操作失败！");
			e.printStackTrace();
		}
	}

}
