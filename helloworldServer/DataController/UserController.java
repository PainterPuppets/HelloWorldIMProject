package helloworldServer.DataController;

import helloworldServer.Mapper.UserMapper;
import helloworldServer.Model.User;
import helloworldServer.Share.DBController;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by painter on 17-1-4.
 */
public class UserController {

    public static User SelectUser(String userid){
        SqlSession session = DBController.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            User user = mapper.selectUserByUserid(userid);
            session.commit();
            return user;
        } catch (Exception e){
            session.rollback();
            return null;
        }
    }

    public static User SelectUser(int i){
        SqlSession session = DBController.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            User user = mapper.selectUserByid(i);
            session.commit();
            return user;
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
            return null;
        }
    }

    public static void UpdateUserInfo(User n_user){
        SqlSession session = DBController.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            mapper.updateUser(n_user);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
    }

    public static Boolean insertUser(String userid,String password,String nickname){
        SqlSession session = DBController.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User n_user = SelectUser(userid);
        if(n_user == null) {
            n_user = new User(userid,password,nickname);
            try {
                mapper.insertUser(n_user);
                session.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                session.rollback();
                return false;
            }
        }else return false;
    }
}
