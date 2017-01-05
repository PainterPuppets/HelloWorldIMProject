package helloworldServer.Share;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * Created by painter on 17-1-4.
 */
public class DBController {

    public static SqlSessionFactory sessionFactory;
    static{
        try {
            Reader reader = Resources.getResourceAsReader("helloworldServer/Share/Configuration.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch (Exception e){
            System.out.println("创建session失败");
            e.printStackTrace();
        }
    }

    public static SqlSession getSession(){
        return sessionFactory.openSession();
    }
}
