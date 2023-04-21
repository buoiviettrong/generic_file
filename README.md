	public static void main(String[] args) throws SQLException, DBException, FatalException, InstantiationException, IllegalAccessException {
		String sql = "SELECT * FROM tcc020_loginauth ;";
		
		PreparedStatement ps = new DBAccessor().getConnection().prepareStatement(sql);
		
		List<Record> lst = query(ps, new check(). new Record());
		for(Record rd : lst) 
			System.out.println(rd);
	}
	
	
	public class Record extends BaseModal{
		public int ENTUSRCD;
		public String ENTPRG;
		public String NUMRSRV1;
		public String NullField;
		
		public Record() {};
		
		@Override
		public Record map(Map<String, Object> params) {
			this.ENTPRG = (String) params.get("ENTPRG");
			this.ENTUSRCD = Integer.valueOf((String) params.get("ENTUSRCD")) ;
			this.NUMRSRV1 = (String) params.get("NUMRSRV1");
			this.NullField = (String) params.get("NullField");
			return this;
 		}
		@Override
		public String toString() {
			return "Record[ENTUSRCD: "+ENTUSRCD+", ENTPRG: "+ENTPRG+", NUMRSRV1: "+NUMRSRV1+", NullField: "+NullField+"]";
		}
		@Override
		public Record newInstance() {
			return new Record();
		}
	}
	public abstract class BaseModal {
		abstract BaseModal map(Map<String, Object> params);
		abstract public BaseModal newInstance();
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends BaseModal> List<T> query(PreparedStatement ps, T clazz) throws SQLException, InstantiationException, IllegalAccessException {
		ResultSet rs = ps.executeQuery();
		List<T> lst = new ArrayList<>();
		while(rs.next()) {
			Map<String, Object> doc = new HashMap<>();
			ResultSetMetaData metaData = rs.getMetaData();
			T clazz1 = (T) clazz.newInstance();
			for(int i = 1; i < metaData.getColumnCount(); i++) {
				String colString = metaData.getColumnName(i);
				doc.put(colString, rs.getObject(i));
			}
			lst.add((T) clazz1.map(doc));
		}
		return lst;
	}
