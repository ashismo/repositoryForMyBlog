import com.core.dao.GroupDataDaoImpl;


public class Main2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GroupDataDaoImpl().displayGroupDataByCompany();
		
		new GroupDataDaoImpl().saveGrp();
		
		new GroupDataDaoImpl().displayGroupDataByCompany();
	}

}
