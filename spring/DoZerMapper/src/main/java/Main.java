import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.business.MapDozerImpl;
import com.config.DozerConfig;
import com.dozerbean.ChildBean;
import com.dozerbean.ParentBean;
import com.entity.Parent;
import com.google.gson.Gson;


public class Main {

	public static void main(String args[]) {
		Main main = new Main();
		main.loadBean();
	}

	private static ParentBean getParentBean() {
		ParentBean pb = new ParentBean();
		pb.setfId(10);
		pb.setmId(11);
		pb.setmAge(40);
		pb.setfAge(40);
		pb.setfName("XYZ");
		pb.setmName("ABC");
		
		List<ChildBean> childList = new ArrayList<ChildBean>();
		for(int i = 0; i < 3; i++) {
			ChildBean c = new ChildBean();
			c.setId(i + 1);
			c.setfId(10);
			c.setmId(11);
			c.setName("MNO" + i);
			c.setAge(12 + i);
			
			childList.add(c);
		}
		pb.setChild(childList);
		return pb;
	}
	
	public void loadBean() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DozerConfig.class);
        MapDozerImpl app = context.getBean(MapDozerImpl.class);
        ParentBean pb = getParentBean();
        
        Gson gson = new Gson();
		String json = gson.toJson(pb);
		System.out.println("Input Parent Bean JSON: " + json);
		
		
        Parent p = new Parent();
        app.mapBean(pb, p);
        System.out.println("Output Parent JSON: " + gson.toJson(p));
	}
	
}
