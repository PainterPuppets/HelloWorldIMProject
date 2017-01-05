package helloworldServer;

import helloworldServer.DataController.RelationshipController;
import helloworldServer.DataController.UserController;
import helloworldServer.Mapper.UserMapper;
import helloworldServer.Model.Relationship;
import helloworldServer.Model.User;

import helloworldServer.Share.DBController;
import org.apache.ibatis.session.*;

import java.util.Vector;

/**
 * Created by painter on 16-12-29.
 */
public class test
{
    public static void main(String []args) {

        /*
        if (UserController.insertUser("painter", "111111", "画家")) {
            System.out.println("数据插入成功");
        }
        User a = UserController.SelectUser("sa");
        User b = UserController.SelectUser("zher");
        System.out.println("a:"+a.uid+" b:"+b.uid);*/


        /*
        RelationshipController.InsertRelation(10000,10004);
        RelationshipController.InsertRelation(10000,10005);
        RelationshipController.InsertRelation(10006,10000);*/
        /*Relationship a = RelationshipController.SelectRelationship(10000,10004);
        if(a != null)
            System.out.println(a.uid);
*/

        Vector<User> usertable = RelationshipController.GetFriendTable(10000);
        for(int i = 0;i<usertable.size();++i){
            System.out.println(usertable.get(i).nickname);
        }
    }


}
