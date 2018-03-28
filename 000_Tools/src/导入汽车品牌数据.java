import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

import com.rzd.framework.kernel.KernelWeb.Utility;
import com.rzd.framework.kernel.util.ExcelReadWriteUtil;
import com.rzd.framework.kernel.util.excel.IteratorCell;
import com.rzd.framework.kernel.util.excel.IteratorSheet;

public class 导入汽车品牌数据 {

	public static void main(String[] args) throws Exception {
		String dbDriverClass = "net.sourceforge.jtds.jdbc.Driver";
		String dbUrl = "jdbc:jtds:sqlserver://172.26.26.204;instanceName=MSSQLSERVER;DatabaseName=rongzhida";
		String dbUserName = "sa";
		String dbPassword = "Szrzd@2015";
		String excelPath = "E:\\SVN\\xxjsb\\trunk\\1_工作周报\\龚俨\\开发文档\\汽车品牌logo.xlsx";
		
		Connection conn = getConnection(dbDriverClass, dbUrl, dbUserName, dbPassword);
		try {
			importData(conn, excelPath);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	private static void importData(Connection conn, String excelPath) throws Exception {
		ExcelReadWriteUtil excelUtil = null;
		try {
			excelUtil = new ExcelReadWriteUtil(new File(excelPath));
			IteratorSheet sheet = excelUtil.read();
			List<IteratorCell> cells = null;
			int seq = 0;
			String brandPinYin = null;
			String brandName = null;
			String brandLogoUrl = null;
			Timestamp time = Utility.getCurrentTimestamp();
			long id = 0;
			while (sheet.read()) {
				brandPinYin = null;
				brandName = null;
				brandLogoUrl = null;
				cells = sheet.next();
				if (cells == null) {
					continue;
				}
				if (cells.size() > 0 && cells.get(0) != null) {
					brandPinYin = cells.get(0).getValue();
					brandPinYin = Utility.noNull(brandPinYin).trim();
				}
				if (cells.size() > 1 && cells.get(1) != null) {
					brandName = cells.get(1).getValue();
					brandName = Utility.noNull(brandName).trim();
				}
				if (cells.size() > 2 && cells.get(2) != null) {
					brandLogoUrl = cells.get(2).getValue();
					brandLogoUrl = Utility.noNull(brandLogoUrl).trim();
				}
				if (!Utility.isEmpty(brandName) && !Utility.isEmpty(brandLogoUrl) && !Utility.isEmpty(brandPinYin)) {
					seq++;
					id++;
					importData(conn, id, brandPinYin, brandName, brandLogoUrl, seq, time);
					System.out.println("导入数据: " + brandPinYin + "\t\t" + brandName + "\t\t" + brandLogoUrl);
				}
			}
		} catch (Exception e) {
			throw e;
		} 
	}


	private static void importData(Connection conn, long id, String brandPinYin, String brandName, String brandLogoUrl, int seq, Timestamp time) throws Exception {
		String sql = "INSERT INTO PRODUCTS_BRAND (ID, PRODUCT_TYPE, BRAND_NAME, BRAND_PIN_YIN, LOGO_URL, SEQ, STATUS, INSERT_TIME, UPDATE_TIME) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement preStat = conn.prepareStatement(sql);
		preStat.setLong(1, id);
		preStat.setString(2, "D");
		preStat.setString(3, brandName);
		preStat.setString(4, brandPinYin);
		preStat.setString(5, brandLogoUrl);
		preStat.setInt(6, seq);
		preStat.setString(7, "A");
		preStat.setTimestamp(8, time);
		preStat.setTimestamp(9, time);
		int count = preStat.executeUpdate();
		if (count <= 0) {
			throw new Exception("导入数据失败: " + brandPinYin + "\t\t" + brandName + "\t\t" + brandLogoUrl);
		}
	}

	private static Connection getConnection(String dbDriverClass, String dbUrl, String dbUserName, String dbPassword) throws Exception {
		Class.forName(dbDriverClass);
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		conn.setAutoCommit(false);
		return conn;
	}
}
