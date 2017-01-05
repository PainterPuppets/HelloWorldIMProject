package helloworldServer.Mapper;

import helloworldServer.Model.User;

/**
 * Created by painter on 17-1-4.
 */
public interface UserMapper {

    public int insertUser(User user) throws Exception;

    public User selectUserByid(int uid) throws Exception;

    public User selectUserByUserid(String userid) throws Exception;

    public int updateUser(User user) throws Exception;
}
