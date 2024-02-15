package connect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.Check;
import model.Discount;
import model.Order;
import model.Orders;
import model.Rate;
import model.Suppliers;
import model.Users;
import tools.Item;
import model.Supplier;


public class ConnectDb{
	protected static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	protected static String dbUrl = "jdbc:sqlserver://rm-uf6da754aw090t6b6ro.sqlserver.rds.aliyuncs.com:3433;DatabaseName=Discount";
	protected static String dbUser = "yiyexiaozhou";
	protected static String dbPwd = "Qwer1234";
	protected static String second = null;
	public static Connection conn = null;
		
		static {
			try {
				if (conn == null) {
					Class.forName(dbClassName).newInstance();
					conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		private ConnectDb() {
		}
		public static ResultSet findForResultSet(String sql) {
			if (conn == null)
				return null;
			long time = System.currentTimeMillis();
			ResultSet rs = null;
			try {
				Statement stmt = null;
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery(sql);
				second = ((System.currentTimeMillis() - time) / 1000d) + "";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return rs;
		}
		// 执行指定查询
		public static ResultSet query(String QueryStr) {
			ResultSet set = findForResultSet(QueryStr);
			return set;
		}
		public static int update(String sql) {
			int result = 0;
			try {
				Statement stmt = conn.createStatement();
				result = stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		public static boolean insert(String sql) {
			boolean result = false;
			try {
				Statement stmt = conn.createStatement();
				result = stmt.execute(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		static ResultSet executeQuery(String sql) {
			try {
				if(conn==null)
				new ConnectDb();
				return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
			}
		}
		public static void close() {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
		public static Users getUser(String name) {
			Users user = new Users();
			ResultSet rs = findForResultSet("select * from T_Authority where Code='" + name + "'");
			try {
				if (rs.next()) {
					user.setLoginName(rs.getString("Code"));
					user.setPassword(rs.getString("Password"));
					user.setAthority(rs.getString("staff"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return user;
		}
		
		public static int addUser(Users ul) {
			return update("insert T_Authority (Code,Password,staff)values('" 
					+ ul.getLoginName() + "','" + ul.getPassword() + "','品牌商')");
		}
		
		public static int delete(String sql) {
			return update(sql);
		}
		
		public static List getUsers() {
			List list = findForList("select * from T_Authority where staff='品牌商'");
			return list;
		}
		
		
		
		public static List findForList(String sql) {
			List<List> list = new ArrayList<List>();
			ResultSet rs = findForResultSet(sql);
			try {
				ResultSetMetaData metaData = rs.getMetaData();
				int colCount = metaData.getColumnCount();
				while (rs.next()) {
					List<String> row = new ArrayList<String>();
					for (int i = 1; i <= colCount; i++) {
						String str = rs.getString(i);
						if (str != null && !str.isEmpty())
							str = str.trim();
						row.add(str);
					}
					list.add(row);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
private static int executeUpdate(String sql) {
			
			try {
				if(conn==null)
					new ConnectDb();
				return conn.createStatement().executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println(e.getMessage());		
				return -1;
			} finally {
			}
		}
		 
		public static int updateUser(String a,String b) {
			return update("update T_Authority set Password='" + b
					+  "' where Code='"
					+ a + "'");
		}
		
		public static List selectSupplier() {
			List list=new ArrayList();
			String sql = "select Cname from T_Supplier";
			ResultSet rs = ConnectDb.executeQuery(sql);
			try {
				while (rs.next()) {
					Suppliers publisherType=new Suppliers();				
					publisherType.setName(rs.getString("Cname"));
					list.add(publisherType);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//ConnectDb.close();
			return list;
			
		}
		public static String selectSupplier1(String a) {
			String b = null;
			String sql = "select Supcode from T_Supplier where Cname='"
					+ a + "'";
			ResultSet rs = ConnectDb.executeQuery(sql);
			try {
				while (rs.next()) {
								
					b=rs.getString("Supcode");
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//ConnectDb.close();
			return b;
			
		}
		
		public static int Insertorder(String Comcode,String Combewrite,String Comnum,Double Comprice,Double Sprice,Double Disprice,Double Finalprice,Date Gooddate,int Paytime,Date uplatedate,String Supcode,String Staff){
			int i=0;
			try{
				String sql="insert T_Order(Comcode,Combewrite,Comnum,Comprice,Sprice,Disprice,Finalprice,Gooddate,Paytime,uplatedate,Supcode,Verification,Staff,Isc) values('"+Comcode+"','"+Combewrite+"','"+Comnum+"','"+Comprice+"','"+Sprice+"','"+Disprice+"','"+Finalprice+"','"+Gooddate+"','"+Paytime+"','"+uplatedate+"','"+Supcode+"','1','"+Staff+"','1')";
				//System.out.println(sql);
				i=ConnectDb.executeUpdate(sql);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			//ConnectDb.close();
			return i;
		}
		
	
		
		public static int UpdateCheckOrder(String ISBN){
			int i=0;
			try{
				String sql="update T_Order set Verification=0 where Comcode='"+ISBN+"'";
				i=ConnectDb.executeUpdate(sql);
			}catch(Exception e){
				e.printStackTrace();
			}
			//ConnectDb.close();
			return i;
			
		}
		// 读取所有供应商信息
		public static List getSuppliersInfos() {
			List list = findForList("select Supcode from T_Supplier");
			return list;
		}
	
		public static List getOrder() {
			List list=new ArrayList();
			String sql = "select * from T_Order";
			ResultSet rs = ConnectDb.executeQuery(sql);
			try {
				while (rs.next()) {
					Order order=new Order();
					order.setComcode(rs.getString(1));
					order.setCombewrite(rs.getString(2));
					order.setComnum(rs.getString(3));
					order.setComprice(rs.getDouble(4));
					order.setSprice(rs.getDouble(5));
					order.setDisprice(rs.getDouble(6));
					
					order.setFinalprice(rs.getDouble(7));
					order.setGooddate(rs.getDate(8));
					order.setPaytime(rs.getInt(9));
					order.setuplatedate(rs.getDate(10));
					order.setSupcode(rs.getString(11));
					order.setVerification(rs.getString(12));
					order.setstaff(rs.getString(13));
					
					list.add(order);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}
		
		// 读取指定供应商信息
		public static Suppliers getSuppliersInfo(String item) {
			//String where = "Cname='" + item.getName() + "'";
			String where=null;
			if (item != null)
				where = "Supcode='" + item + "'";
			Suppliers info = new Suppliers();
			ResultSet set = findForResultSet("select * from T_Supplier where " + where);
			try {
				if (set.next()) {
					info.setId(set.getString("Supcode").trim());
					info.setName(set.getString("Cname").trim());
					info.setProperty(set.getString("Property").trim());
					info.setScope(set.getString("Scope").trim());
					info.setCaddress(set.getString("Caddress").trim());
					info.setNumber(set.getString("Number").trim());
					info.setSarea(set.getString("Sarea").trim());
					info.setFarea(set.getString("Farea").trim());
					info.setPhone(set.getString("Phone").trim());
					info.setEmail(set.getString("Email").trim());
					info.setStuff(set.getString("Stuff").trim());
					info.setSphone(set.getString("Sphone").trim());
					info.setFA(set.getString("FA").trim());
					info.setRC(set.getString("RC").trim());
					info.setLicense(set.getString("License").trim());
					info.setIDcard(set.getString("IDcard").trim());
					info.setAuthorization(set.getString("Authorizations").trim());
					info.setStax(set.getString("Stax").trim());
					info.setLtax(set.getString("Ltax").trim());
					info.setCode(set.getString("CCode").trim());
					info.setYz(set.getString("Yz").trim());
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return info;
		}
		// 修改供应商信息的方法
		public static int updateSuppliers(Suppliers suppliersInfo) {
			return update("update T_Supplier set Property='" + suppliersInfo.getProperty()
					+ "',Scope='" + suppliersInfo.getScope() + "',Caddress='"
					+ suppliersInfo.getCaddress() + "',Number='" + suppliersInfo.getNumber()
					+ "',Sarea='" + suppliersInfo.getSarea() + "',Farea='" + suppliersInfo.getFarea()
					+ "',Phone='" + suppliersInfo.getPhone() + "',Email='"
					+ suppliersInfo.getEmail() 
					+ "',Stuff='" + suppliersInfo.getStuff()+ "',Sphone='" + suppliersInfo.getSphone()+ "',FA='" + suppliersInfo.getFA()
					+ "',RC='" + suppliersInfo.getRC()+ "',License='" + suppliersInfo.getLicense()
					+ "',IDcard='" + suppliersInfo.getIDcard()+ "',Authorizations='" + suppliersInfo.getAuthorization()
					+ "',Stax='" + suppliersInfo.getStax()+ "',Ltax='" + suppliersInfo.getLtax()
					+ "',CCode='" + suppliersInfo.getCode()+ "',Yz='" + suppliersInfo.getYz()
					+ "' where Supcode='" + suppliersInfo.getId() + "'");
		}
		// 添加供应商信息的方法
		

		
		
		public static int addSuppliers(String Supcode,String Cname,String Property,String Scope,String Caddress,String Number,String Sarea,String Farea,String Phone,String Email,String Stuff,String Sphone,String FA,String RC,String License,String IDcard,String Authorizations,String Stax,String Ltax,String CCode,String Yz){
			int i=0;
			try{
				String sql="insert T_Supplier (Supcode,Cname,Property,Scope,Caddress,Number,Sarea,Farea,Phone,Email,Stuff,Sphone,FA,RC,License,IDcard,Authorizations,Stax,Ltax,CCode,Yz) values"							
						+ "('"+Supcode+"','"+Cname+"','"+Property+"','"+Scope+"','"+Caddress+"','"+Number+"','"+Sarea+"','"+Farea+"','"+Phone+"','"+Email+"','"+Stuff+"','"+Sphone+"','"+FA+"','"+RC+"','"+License+"','"+IDcard+"','"+Authorizations+"','"+Stax+"','"+Ltax+"','"+CCode+"','"+Yz+"')";
				 //System.out.println(sql);
				i=ConnectDb.executeUpdate(sql);
				//System.out.println(i);
			}catch(Exception e){
				e.printStackTrace();
			}
			//ConnectDb.close();
			return i;
		} 
		 
		 
		 public static String selectSupplier2() {
			 
				String a = null;
				String sql = "select max(Supcode) from T_Supplier";
				ResultSet rs = ConnectDb.executeQuery(sql);
				try {
					while (rs.next()) {				
						a=rs.getString(1);				
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
				//ConnectDb.close();
				return a; 
		 }
		 public static int addUser1(Users ul) {
				return update("insert T_Authority (Code,Password,staff)values('" 
						+ ul.getLoginName() + "','" + ul.getPassword() + "','供应商')");
			}
		 
			public static List getRate() {
				List list=new ArrayList();
				String sql = "select * from T_Rate where Updatetime =(select max(Updatetime) from T_Rate)";
				ResultSet rs = ConnectDb.executeQuery(sql);
				try {
					while (rs.next()) {
						Rate order=new Rate();
						order.setRate1(rs.getDouble("Rate1"));
						order.setRate2(rs.getDouble("Rate2"));
						order.setRate3(rs.getDouble("Rate3"));
						order.setUpdatedate(rs.getDate("Updatetime"));
						
						list.add(order);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return list;
			}	 
			public static float getRate1() {
				float a=0;
				String sql = "select Rate1 from T_Rate where Updatetime =(select max(Updatetime) from T_Rate)";
				ResultSet rs = ConnectDb.executeQuery(sql);
				try {
					while (rs.next()) {
						
						a=rs.getFloat("Rate1");
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return a;
			}	 
			public static int addRate(int Supcode1,Double Supcode,Double Cname,Double Property,Date Scope){
				int i=0;
				try{
					String sql="insert T_Rate (id,Rate1,Rate2,Rate3,Updatetime) values"							
							+ "('"+Supcode1+"','"+Supcode+"','"+Cname+"','"+Property+"','"+Scope+"')";
					 //System.out.println(sql);
					i=ConnectDb.executeUpdate(sql);
					//System.out.println(i);
				}catch(Exception e){
					e.printStackTrace();
				}
				//ConnectDb.close();
				return i;
			} 
			
			public static int selectRateid() {
				 
				int a = 0;
				String sql = "select max(id) from T_Rate";
				ResultSet rs = ConnectDb.executeQuery(sql);
				try {
					while (rs.next()) {				
						a=rs.getInt(1);				
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
				//ConnectDb.close();
				return a; 
		 }
			 public static Date selectMaxtime(String Supcode1) {
				 
					Date a = null;
					String sql = "select max(Gooddate) from View_unprolong where Incode='"+Supcode1+"'";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {				
							a=rs.getDate(1);				
					}
					}catch (Exception e) {
						e.printStackTrace();
					}
					//ConnectDb.close();
					return a; 
			 }	
			 public static List getUnpro1(String Supcode1) {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Incode='"+Supcode1+"' and Isaudit='0' and Incode not in (select Incode from T_Prolong)";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}	 
			 public static List getUnpro2(String Supcode1) {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Supcode ='"+Supcode1+"' and Isaudit='0' and Incode not in (select Incode from T_Prolong)";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}	 
			 public static List getUnpro3() {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Isaudit='0' and Incode not in (select Incode from T_Prolong)";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}	 
			 public static List getPro() {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Incode in (select Incode from T_Prolong where Verification='0')";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}	 
			 public static List getPro2() {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Incode in (select Incode from T_Prolong where Verification='1')";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}	  
			 public static String getVerification(String Supcode1) {
				 
					String a = null;
					String sql = "select Verification from T_Prolong where Incode ='"+Supcode1+"'";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {				
							a=rs.getString(1);				
					}
					}catch (Exception e) {
						e.printStackTrace();
					}
					//ConnectDb.close();
					return a; 
			 }
			 public static String getCost(String Supcode1) {
				 
					String a = null;
					String sql = "select Cost from T_Prolong where Incode ='"+Supcode1+"'";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {				
							a=rs.getString(1);				
					}
					}catch (Exception e) {
						e.printStackTrace();
					}
					//ConnectDb.close();
					return a; 
			 }	
			 
			 
			
			 public static int InsertProlong(String Supcode1,String Supcode,String Cname,String Property,String Scope,String Scope1){
					int i=0;
					try{
						String sql="insert T_Prolong (Incode,Crate,Yrate,Period,Urate,Cost,Verification) values"							
								+ "('"+Supcode1+"','"+Supcode+"','"+Cname+"','"+Property+"','"+Scope+"','"+Scope1+"','0')";
						 //System.out.println(sql);
						i=ConnectDb.executeUpdate(sql);
						//System.out.println(i);
					}catch(Exception e){
						e.printStackTrace();
					}
					//ConnectDb.close();
					return i;
				} 
			 public static int InsertProlong1(String Supcode1,String Supcode,String Cname,String Property,String Scope,String Scope1){
					int i=0;
					try{
						String sql="insert T_Prolong (Incode,Crate,Yrate,Period,Urate,Cost,Verification) values"							
								+ "('"+Supcode1+"','"+Supcode+"','"+Cname+"','"+Property+"','"+Scope+"','"+Scope1+"','1')";
						 //System.out.println(sql);
						i=ConnectDb.executeUpdate(sql);
						//System.out.println(i);
					}catch(Exception e){
						e.printStackTrace();
					}
					//ConnectDb.close();
					return i;
				} 
			 public static List getUndis1(String Supcode1,String Supcode) {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Incode='"+Supcode1+"' and Supcode='"+Supcode+"' and Isaudit='0' and Incode not in (select Incode from T_Discount)";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}	
			 public static List getUndis3(String Supcode1) {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Incode='"+Supcode1+"' and Incode in (select Incode from T_Discount where Verification='0')";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}	
			 public static List getUndis2(String Supcode) {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Supcode='"+Supcode+"' and Isaudit='0' and Incode not in (select Incode from T_Discount)";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}
			 public static List getUndis4() {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Incode in (select Incode from T_Discount where Verification='0')";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}
			 public static List getDis(String Supcode) {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Supcode='"+Supcode+"' and Incode in (select Incode from T_Discount where Verification='0')";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}	
			 public static List getDis2(String Supcode) {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Supcode='"+Supcode+"' and Incode in (select Incode from T_Discount where Verification='1')";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}	
			 public static String getVerification1(String Supcode1) {
				 
					String a = null;
					String sql = "select Verification from T_Discount where Incode ='"+Supcode1+"'";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {				
							a=rs.getString(1);				
					}
					}catch (Exception e) {
						e.printStackTrace();
					}
					//ConnectDb.close();
					return a; 
			 }
			 public static String getCost1(String Supcode1) {
				 
					String a = null;
					String sql = "select Cost from T_Discount where Incode ='"+Supcode1+"'";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {				
							a=rs.getString(1);				
					}
					}catch (Exception e) {
						e.printStackTrace();
					}
					//ConnectDb.close();
					return a; 
			 }	
			 public static int getDay(String Supcode1) {
				 
					int a = 0;
					String sql = "select Period from T_Discount where Incode ='"+Supcode1+"'";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {				
							a=rs.getInt(1);				
					}
					}catch (Exception e) {
						e.printStackTrace();
					}
					//ConnectDb.close();
					return a; 
			 }	
			 public static int InsertDiscount(String Supcode1,String Supcode,String Cname,int Property,String Scope,String Scope1){
					int i=0;
					try{
						String sql="insert T_Discount (Incode,Crate,Yrate,Period,Urate,Cost,Verification) values"							
								+ "('"+Supcode1+"','"+Supcode+"','"+Cname+"','"+Property+"','"+Scope+"','"+Scope1+"','0')";
						 //System.out.println(sql);
						i=ConnectDb.executeUpdate(sql);
						//System.out.println(i);
					}catch(Exception e){
						e.printStackTrace();
					}
					//ConnectDb.close();
					return i;
				} 
			 public static int InsertDiscount1(String Supcode1,String Supcode,String Cname,int Property,String Scope,String Scope1){
					int i=0;
					try{
						String sql="insert T_Discount (Incode,Crate,Yrate,Period,Urate,Cost,Verification) values"							
								+ "('"+Supcode1+"','"+Supcode+"','"+Cname+"','"+Property+"','"+Scope+"','"+Scope1+"','1')";
						 //System.out.println(sql);
						i=ConnectDb.executeUpdate(sql);
						//System.out.println(i);
					}catch(Exception e){
						e.printStackTrace();
					}
					//ConnectDb.close();
					return i;
				} 
			 public static List getPro1() {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Incode in (select Incode from T_Prolong where Verification='1')";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}	
			 public static int UpdateProlong(String ISBN){
					int i=0;
					try{
						String sql="update T_Prolong set Verification=0 where Incode='"+ISBN+"'";
						i=ConnectDb.executeUpdate(sql);
					}catch(Exception e){
						e.printStackTrace();
					}
					//ConnectDb.close();
					return i;
					
				}
			 public static List getDis1() {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Incode in (select Incode from T_Discount where Verification='1')";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}	
			 public static List getDis3() {
					List list=new ArrayList();
					String sql = "select * from View_unprolong where Incode in (select Incode from T_Discount where Verification='0')";
					ResultSet rs = ConnectDb.executeQuery(sql);
					try {
						while (rs.next()) {
							
							Discount order=new Discount();
							order.setIncode(rs.getString("Incode"));
							order.setSupcode(rs.getString("Supcode"));
							order.setFinalprice(rs.getString("Finalprice"));
							order.setpaytime(rs.getInt("paytime"));
							
							list.add(order);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return list;
				}	
			 public static int UpdateDiscount(String ISBN){
					int i=0;
					try{
						String sql="update T_Discount set Verification=0 where Incode='"+ISBN+"'";
						i=ConnectDb.executeUpdate(sql);
					}catch(Exception e){
						e.printStackTrace();
					}
					//ConnectDb.close();
					return i;
					
				}
			 public static int updateOrder(String a,String b,String c,String d,String e,String f,String g,String h,String i) {
					return update("update T_Order set Combewrite='" + b
							+  "',Comnum='" + c +"',Comprice='" + d +"',Sprice='" + e +"',Disprice='" + f +"'"
									+ ",Finalprice='" + g +"',Gooddate='" + h +"',Paytime='" + i +"' where Comcode='"
							+ a + "'");
				}
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 public static Orders getCustomersInfo(Item item) {
					String where = "Comcode='" + item.getId() + "'";
					//if (item.getId() != null)
					//where = "Combewrite='" + item.getId() + "'";
					
					Orders info = new Orders();
					ResultSet set = findForResultSet("select * from T_Order where " + where);
					try {
						if (set.next()) {
							info.setComcode(set.getString("Comcode").trim());
							info.setCombewrite(set.getString("Combewrite").trim());
							info.setComprice(set.getString("Comprice").trim());
							info.setComnum(set.getString("Comnum").trim());
							info.setSprice(set.getString("Sprice").trim());
							info.setFinalprice(set.getString("Finalprice").trim());
							info.setGooddate(set.getDate("Gooddate"));		
							info.setPaytime(set.getString("Paytime").trim());
							info.setUplatedate(set.getDate("Uplatedate"));
							info.setSupcode(set.getString("Supcode").trim());
						
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return info;
				}
			 
				
				//public static List selectInfo(String Comcode) {
				public static List selectInfo() {
					List list=new ArrayList();
					String sql = "select * from View_1 where Correct=0 and Isaudit=1 and Comcode in (select min(Comcode)from View_1 group by Auditcode) and Incode in (select min(Incode) from View_1 group by Auditcode)";
					ResultSet rs = ConnectDb.findForResultSet(sql);
					try {
						while (rs.next()) {
							Check check=new Check();
							check.setCname(rs.getString("Cname"));
							check.setAuditcode(rs.getString("Auditcode"));
							
							list.add(check);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					//ConnectDb.close();
					return list;
					
				}
				 
				public static List getInfo(String au) {
					List l=new ArrayList();
					String sql1 = "select * from View_1 where Auditcode='" + au + "' and Incode in (select min(Incode) from View_1 group by Auditcode)";
					ResultSet i = ConnectDb.findForResultSet(sql1);
					//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
					try {
						while (i.next()) {
							Check checks = new Check();
							checks.setComcode(i.getString("Comcode").trim());
							checks.setComnum(i.getInt("Comnum"));
							checks.setFinalprice(i.getDouble("Finalprice"));
							checks.setCombewrite(i.getString("Combewrite").trim());
							checks.setGooddate(i.getDate("Gooddate"));
							checks.setPaytime(i.getInt("Paytime"));
							l.add(checks);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return l;
				}
				public static List getInfo2(String au) {
					List l2=new ArrayList();
					String sql2 = "select * from View_1 where Auditcode='" + au + "' and Comcode in (select min(Comcode) from View_1 group by Auditcode)";
					ResultSet i1 = ConnectDb.findForResultSet(sql2);
					//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
					try {
						while (i1.next()) {
							Check checks1 = new Check();
							checks1.setIncode(i1.getString("Incode").trim());
							checks1.setComnum1(i1.getInt("Number"));
							checks1.setFinalprice1(i1.getDouble("Expr3"));
							checks1.setCombewrite1(i1.getString("Productname").trim());
							checks1.setIntime(i1.getDate("Intime"));
							//checks1.setPaytime(i1.getInt("Paytime"));
							l2.add(checks1);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return l2;
				}

				public static List getInfo3(int is) {
				//public static List getInfo3() {
				
					List l3=new ArrayList();
					String sql3 = "select * from View_1 where Isaudit=" + is + "";
					//String sql3 = "select * from View_1 where Isaudit='" + is + "'";
					//String sql3 = "select * from View_1 where Isaudit=0";
					ResultSet i2 = ConnectDb.findForResultSet(sql3);
					//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
					try {
						while (i2.next()) {
							Check checks2 = new Check();
						    checks2.setCname(i2.getString("Cname").trim());
						    checks2.setSupcode(i2.getString("Supcode").trim());
							checks2.setComcode(i2.getString("Comcode").trim());
							checks2.setIncode(i2.getString("Incode").trim());
							checks2.setAuditcode(i2.getString("Auditcode").trim());
							checks2.setComnum(i2.getInt("Comnum"));
							checks2.setComnum1(i2.getInt("Number"));
							checks2.setFinalprice(i2.getDouble("Finalprice"));
							//checks2.setFinalprice1(i2.getDouble("Finalprice"));
							checks2.setCombewrite(i2.getString("Productname").trim());
							checks2.setGooddate(i2.getDate("Gooddate"));
							checks2.setPaytime(i2.getInt("Paytime"));
							checks2.setAuditcode(i2.getString("Auditcode").trim());
							checks2.setCombewrite1(i2.getString("Expr3").trim());
							//checks1.setPaytime(i1.getInt("Paytime"));
							l3.add(checks2);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return l3;
				}
				public static List getInfo4(int is1,String su) {
					
					List l4=new ArrayList();
					//String sql3 = "select * from View_1 where Isaudit='" + is + "')";
					String sql4 = "select * from View_1 where Isaudit=" + is1 + " and Cname='" + su + "'";
					ResultSet i3 = ConnectDb.findForResultSet(sql4);
					//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
					try {
						while (i3.next()) {
							Check checks3 = new Check();
							checks3.setSupcode(i3.getString("Supcode").trim());
							checks3.setCname(i3.getString("Cname").trim());
							checks3.setComcode(i3.getString("Comcode").trim());
							checks3.setIncode(i3.getString("Incode").trim());
							checks3.setAuditcode(i3.getString("Auditcode").trim());
							checks3.setComnum(i3.getInt("Comnum"));
							checks3.setFinalprice(i3.getDouble("Finalprice"));
							//checks2.setFinalprice1(i2.getDouble("Finalprice"));
							checks3.setCombewrite(i3.getString("Productname").trim());
							checks3.setGooddate(i3.getDate("Gooddate"));
							checks3.setPaytime(i3.getInt("Paytime"));
							//checks1.setPaytime(i1.getInt("Paytime"));
							l4.add(checks3);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return l4;
				}
		public static List getInfo5(int is1,String dit) {
					
					List l5=new ArrayList();
					//String sql3 = "select * from View_1 where Isaudit='" + is + "')";
					String sql5 = "select * from View_1 where Isaudit=" + is1 + " and Incode='" + dit + "'";
					ResultSet i4 = ConnectDb.findForResultSet(sql5);
					//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
					try {
						while (i4.next()) {
							Check checks4 = new Check();
							checks4.setSupcode(i4.getString("Supcode").trim());
							checks4.setCname(i4.getString("Cname").trim());
							checks4.setComcode(i4.getString("Comcode").trim());
							checks4.setIncode(i4.getString("Incode").trim());
							checks4.setAuditcode(i4.getString("Auditcode").trim());
							checks4.setComnum(i4.getInt("Comnum"));
							checks4.setFinalprice(i4.getDouble("Finalprice"));
							//checks2.setFinalprice1(i2.getDouble("Finalprice"));
							checks4.setCombewrite(i4.getString("Productname").trim());
							checks4.setGooddate(i4.getDate("Gooddate"));
							checks4.setPaytime(i4.getInt("Paytime"));
							//checks1.setPaytime(i1.getInt("Paytime"));
							l5.add(checks4);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return l5;
				}
		public static List getInfo6(int is,String sup) {
			//public static List getInfo3() {
			
				List l3=new ArrayList();
				String sql3 = "select * from View_1 where Isaudit=" + is + " and Supcode='" + sup + "'";
				//String sql3 = "select * from View_1 where Isaudit='" + is + "'";
				//String sql3 = "select * from View_1 where Isaudit=0";
				ResultSet i2 = ConnectDb.findForResultSet(sql3);
				//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
				try {
					while (i2.next()) {
						Check checks2 = new Check();
					
						checks2.setComcode(i2.getString("Comcode").trim());
						checks2.setIncode(i2.getString("Incode").trim());
						checks2.setAuditcode(i2.getString("Auditcode").trim());
						checks2.setComnum(i2.getInt("Comnum"));
						checks2.setComnum1(i2.getInt("Number"));
						checks2.setFinalprice(i2.getDouble("Finalprice"));
						checks2.setFinalprice1(i2.getDouble("Expr3"));
						//checks2.setFinalprice1(i2.getDouble("Finalprice"));
						checks2.setCombewrite(i2.getString("Combewrite").trim());
						checks2.setGooddate(i2.getDate("Gooddate"));
						checks2.setPaytime(i2.getInt("Paytime"));
						checks2.setIntime(i2.getDate("Intime"));
						checks2.setAuditcode(i2.getString("Auditcode").trim());
						checks2.setCombewrite1(i2.getString("Productname").trim());
						//checks1.setPaytime(i1.getInt("Paytime"));
						l3.add(checks2);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return l3;
			}

		public static List getInfo7(int is) {
			//public static List getInfo3() {
			
				List l3=new ArrayList();
				String sql3 = "select * from View_1 where Isaudit=" + is + "";
				//String sql3 = "select * from View_1 where Isaudit='" + is + "'";
				//String sql3 = "select * from View_1 where Isaudit=0";
				ResultSet i2 = ConnectDb.findForResultSet(sql3);
				//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
				try {
					while (i2.next()) {
						Check checks2 = new Check();
					
						checks2.setComcode(i2.getString("Comcode").trim());
						checks2.setIncode(i2.getString("Incode").trim());
						checks2.setAuditcode(i2.getString("Auditcode").trim());
						checks2.setComnum(i2.getInt("Comnum"));
						checks2.setComnum1(i2.getInt("Number"));
						checks2.setFinalprice(i2.getDouble("Finalprice"));
						checks2.setFinalprice1(i2.getDouble("Expr3"));
						//checks2.setFinalprice1(i2.getDouble("Finalprice"));
						checks2.setCombewrite(i2.getString("Combewrite").trim());
						checks2.setGooddate(i2.getDate("Gooddate"));
						checks2.setPaytime(i2.getInt("Paytime"));
						checks2.setIntime(i2.getDate("Intime"));
						checks2.setAuditcode(i2.getString("Auditcode").trim());
						checks2.setCombewrite1(i2.getString("Productname").trim());
						//checks1.setPaytime(i1.getInt("Paytime"));
						l3.add(checks2);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return l3;
			}
		
		
		
		public static List select1(String order) {
			
			List l6=new ArrayList();
			//String sql3 = "select * from View_1 where Isaudit='" + is + "')";
			String sql6 = "select * from T_Order where Isc=1 and Verification=0 and Supcode='" + order + "'";
			ResultSet i5 = ConnectDb.findForResultSet(sql6);
			//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
			try {
				while (i5.next()) {
					Check checks5 = new Check();
					checks5.setComcode(i5.getString("Comcode").trim());
					checks5.setCombewrite(i5.getString("Combewrite").trim());
					checks5.setComnum(i5.getInt("Comnum"));
					checks5.setFinalprice(i5.getDouble("Finalprice"));
					checks5.setGooddate(i5.getDate("Gooddate"));
					checks5.setPaytime(i5.getInt("Paytime"));
					//checks5.setComnum(i5.getInt("Supcode"));
					//checks5.setFinalprice(i5.getDouble(""));
					//checks2.setFinalprice1(i2.getDouble("Finalprice"));
					//checks5.setCombewrite(i5.getString("Productname").trim());
					//checks5.setGooddate(i5.getDate("Gooddate"));
					//checks5.setPaytime(i5.getInt("Paytime"));
					//checks1.setPaytime(i1.getInt("Paytime"));
					l6.add(checks5);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return l6;
		}

		public static List select2(String is) {
			
			List l7=new ArrayList();
			//String sql3 = "select * from View_1 where Isaudit='" + is + "')";
			String sql7 = "select * from T_Order where Comcode='" + is + "'";
			ResultSet i6 = ConnectDb.findForResultSet(sql7);
			//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
			try {
				while (i6.next()) {
					Check checks6 = new Check();
					checks6.setComnum(i6.getInt("Comnum"));
					checks6.setFinalprice(i6.getDouble("Finalprice"));
					l7.add(checks6);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return l7;
		}

		public static List select3(int is,int co,String sup) {
			
			List l8=new ArrayList();
			//String sql3 = "select * from View_1 where Isaudit='" + is + "')";
			String sql8 = "select * from View_1 where Isaudit=" + is + " and Correct=" + co + " and Supcode='" + sup + "' and Comcode in (select min(Comcode) from View_1 group by Auditcode)";
			ResultSet i7 = ConnectDb.findForResultSet(sql8);
			//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
			try {
				while (i7.next()) {
					Check checks7 = new Check();
					checks7.setIncode(i7.getString("Incode"));
					checks7.setIntime(i7.getDate("Intime"));
					checks7.setCombewrite1(i7.getString("Productname"));
					checks7.setComnum1(i7.getInt("Number"));
					checks7.setPrice(i7.getDouble("Price"));
					checks7.setFinalprice1(i7.getDouble("Expr3"));
					checks7.setdrawer(i7.getString("Drawer"));
					l8.add(checks7);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return l8;
		}
		public static List select4(int is,String inc,String Sup) {
			
			List l9=new ArrayList();
			//String sql3 = "select * from View_1 where Isaudit='" + is + "')";
			String sql9 = "select * from View_1 where Isaudit=" + is + " and Incode='" + inc + "' and Supcode='" + Sup + "'";
			ResultSet i8 = ConnectDb.findForResultSet(sql9);
			//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
			try {
				while (i8.next()) {
					Check checks7 = new Check();
					checks7.setIncode(i8.getString("Incode"));
					checks7.setIntime(i8.getDate("Intime"));
					checks7.setComcode(i8.getString("Comcode").trim());
					checks7.setCombewrite1(i8.getString("Productname"));
					checks7.setCombewrite(i8.getString("Combewrite"));
					checks7.setAuditcode(i8.getString("Auditcode"));
					checks7.setGooddate(i8.getDate("Gooddate"));
					checks7.setComnum1(i8.getInt("Number"));
					checks7.setComnum(i8.getInt("Comnum"));
					//checks7.setPrice(i8.getDouble("Price"));
					checks7.setFinalprice1(i8.getDouble("Expr3"));
					checks7.setFinalprice(i8.getDouble("Finalprice"));
					checks7.setPaytime(i8.getInt("Paytime"));
					//checks7.setdrawer(i8.getString("Drawer"));
					l9.add(checks7);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return l9;
		}

		public static List select5(int is,String Com,String sup) {
			
			List l9=new ArrayList();
			//String sql3 = "select * from View_1 where Isaudit='" + is + "')";
			String sql9 = "select * from View_1 where Isaudit=" + is + " and Comcode='" + Com + "' and Supcode='" + sup + "'";
			ResultSet i8 = ConnectDb.findForResultSet(sql9);
			//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
			try {
				while (i8.next()) {
					Check checks7 = new Check();
					checks7.setIncode(i8.getString("Incode"));
					checks7.setComcode(i8.getString("Comcode").trim());
					checks7.setIntime(i8.getDate("Intime"));
					checks7.setCombewrite1(i8.getString("Productname"));
					checks7.setCombewrite(i8.getString("Combewrite"));
					checks7.setAuditcode(i8.getString("Auditcode"));
					checks7.setGooddate(i8.getDate("Gooddate"));
					checks7.setComnum1(i8.getInt("Number"));
					checks7.setComnum(i8.getInt("Comnum"));
					//checks7.setPrice(i8.getDouble("Price"));
					checks7.setFinalprice1(i8.getDouble("Expr3"));
					checks7.setFinalprice(i8.getDouble("Finalprice"));
					checks7.setPaytime(i8.getInt("Paytime"));
					//checks7.setdrawer(i8.getString("Drawer"));
					l9.add(checks7);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return l9;
		}

		public static List select6(String sup) {
			
			List l9=new ArrayList();
			//String sql3 = "select * from View_1 where Isaudit='" + is + "')";
			String sql9 = "select * from T_Supplier where Supcode='" + sup + "'";
			ResultSet i8 = ConnectDb.findForResultSet(sql9);
			//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
			try {
				while (i8.next()) {
					Supplier s= new Supplier();
					
					
					s.setCname(i8.getString("Cname").trim());
					s.setStax(i8.getString("Stax").trim());
					s.setPhone(i8.getString("Phone").trim());
					s.setLtax(i8.getString("Ltax"));
					s.setCcode(i8.getString("Ccode"));
					s.setCaddress(i8.getString("Caddress"));
					
					l9.add(s);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return l9;
		}

	public static List select7(int is,String inc) {
			
			List l9=new ArrayList();
			//String sql3 = "select * from View_1 where Isaudit='" + is + "')";
			String sql9 = "select * from View_1 where Isaudit=" + is + " and Incode='" + inc + "'";
			ResultSet i8 = ConnectDb.findForResultSet(sql9);
			//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
			try {
				while (i8.next()) {
					Check checks7 = new Check();
					checks7.setIncode(i8.getString("Incode"));
					checks7.setIntime(i8.getDate("Intime"));
					checks7.setComcode(i8.getString("Comcode").trim());
					checks7.setCombewrite1(i8.getString("Productname"));
					checks7.setCombewrite(i8.getString("Combewrite"));
					checks7.setAuditcode(i8.getString("Auditcode"));
					checks7.setGooddate(i8.getDate("Gooddate"));
					checks7.setComnum1(i8.getInt("Number"));
					checks7.setComnum(i8.getInt("Comnum"));
					//checks7.setPrice(i8.getDouble("Price"));
					checks7.setFinalprice1(i8.getDouble("Expr3"));
					checks7.setFinalprice(i8.getDouble("Finalprice"));
					checks7.setPaytime(i8.getInt("Paytime"));
					//checks7.setdrawer(i8.getString("Drawer"));
					l9.add(checks7);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return l9;
		}


	public static List select8(int is,String Com) {
		
		List l9=new ArrayList();
		//String sql3 = "select * from View_1 where Isaudit='" + is + "')";
		String sql9 = "select * from View_1 where Isaudit=" + is + " and Comcode='" + Com + "'";
		ResultSet i8 = ConnectDb.findForResultSet(sql9);
		//ResultSet i = findForResultSet("select * from View_1 where Auditcode='" + au + "'");
		try {
			while (i8.next()) {
				Check checks7 = new Check();
				checks7.setIncode(i8.getString("Incode"));
				checks7.setComcode(i8.getString("Comcode").trim());
				checks7.setIntime(i8.getDate("Intime"));
				checks7.setCombewrite1(i8.getString("Productname"));
				checks7.setCombewrite(i8.getString("Combewrite"));
				checks7.setAuditcode(i8.getString("Auditcode"));
				checks7.setGooddate(i8.getDate("Gooddate"));
				checks7.setComnum1(i8.getInt("Number"));
				checks7.setComnum(i8.getInt("Comnum"));
				//checks7.setPrice(i8.getDouble("Price"));
				checks7.setFinalprice1(i8.getDouble("Expr3"));
				checks7.setFinalprice(i8.getDouble("Finalprice"));
				checks7.setPaytime(i8.getInt("Paytime"));
				//checks7.setdrawer(i8.getString("Drawer"));
				l9.add(checks7);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l9;
	}




				

		public static int InsertInvoice(String Invoice,String name,Date Intime,Double finalprice,String drawer,Double price,int number,String sup){
			int i=0;
			try{
				String sql="insert into T_Invoice(Incode,Productname,Intime,Finalprice,Drawer,Price,Number,Supcode) values('"+Invoice+"','"+name+"','"+Intime+"','"+finalprice+"','"+drawer+"','"+price+"','"+number+"','"+sup+"')";
				i=ConnectDb.executeUpdate(sql);
			}catch(Exception e){
				e.printStackTrace();
			}
			//ConnectDb.close();
			return i;
		} 

		public static int InsertRecon(String Com,String In,String Auditco,int Is){
			int i1=0;
			try{
				String sql="insert into T_Reconciliation(Comcode,Incode,Auditcode,Isaudit) values('"+Com+"','"+In+"','"+Auditco+"','"+Is+"')";
				i1=ConnectDb.executeUpdate(sql);
			}catch(Exception e){
				e.printStackTrace();
			}
			//ConnectDb.close();
			return i1;
		} 


		public static int UpdateRec(int iscore,Date adate,String audit){
			int i2=0;
			try{
				String sql="update T_Reconciliation set Isaudit='"+iscore+"',Adate='"+adate+"'"+"where Auditcode='"+audit+"'";
			//	String sql1="update T_Authority set Paw='"+password+"'"+"where Code='"+ManagerCode+"'";	
				i2=ConnectDb.executeUpdate(sql);
			//	ConnectDb.executeUpdate(sql1);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return i2;
		}

		public static int UpdateInc(int isc,String in){
			int i3=0;
			try{
				String sql="update T_Invoice set Correct='"+isc+"'"+"where Incode='"+in+"'";
			//	String sql1="update T_Authority set Paw='"+password+"'"+"where Code='"+ManagerCode+"'";	
				i3=ConnectDb.executeUpdate(sql);
			//	ConnectDb.executeUpdate(sql1);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return i3;
		}

		public static int UpdateInco(String in,String incode,String name,int num,double price,double finalprice,int cor){
			int i4=0;
			try{
				String sql="update T_Invoice set Incode='"+incode+"',Productname='"+name+"',Number='"+num+"',Price='"+price+"',Correct="+cor+",Finalprice='"+finalprice+"'"+"where Incode='"+in+"'";
			//	String sql1="update T_Authority set Paw='"+password+"'"+"where Code='"+ManagerCode+"'";	
				i4=ConnectDb.executeUpdate(sql);
			//	ConnectDb.executeUpdate(sql1);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return i4;
		} 

		public static int UpdateCom(int isc,String in){
			int i4=0;
			try{
				String sql="update T_Order set Isc='"+isc+"'"+"where Comcode='"+in+"'";
			//	String sql1="update T_Authority set Paw='"+password+"'"+"where Code='"+ManagerCode+"'";	
				i4=ConnectDb.executeUpdate(sql);
			//	ConnectDb.executeUpdate(sql1);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return i4;
		}
		 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
}
