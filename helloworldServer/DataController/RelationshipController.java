package helloworldServer.DataController;

import helloworldServer.Mapper.RelationshipMapper;
import helloworldServer.Mapper.UserMapper;
import helloworldServer.Model.Relationship;
import helloworldServer.Model.User;
import helloworldServer.Share.DBController;
import org.apache.ibatis.session.SqlSession;

import java.util.Vector;

/**
 * Created by painter on 17-1-4.
 */
public class RelationshipController {

    public static Vector<User> GetFriendTable(int uid){
        Vector<Relationship> a = SelectRelationship(uid);
        Vector<Relationship> b = SelectFriendRelationship(uid);
        Vector<User> n = new Vector<User>();
        for(int i = 0;i < a.size();++i){
            User user = UserController.SelectUser(a.get(i).frienduid);
            n.add(user);
        }
        for(int i = 0;i < b.size();++i){
            User user = UserController.SelectUser(b.get(i).uid);
            n.add(user);
        }
        return n;
    }

    public static Relationship SelectRelationship(int uid,int frienduid){
        SqlSession session = DBController.getSession();
        RelationshipMapper mapper = session.getMapper(RelationshipMapper.class);
        try {
            Relationship relationship = mapper.selectRelation(uid,frienduid);
            session.commit();
            return relationship;
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
            return null;
        }
    }

    public static Vector<Relationship> SelectRelationship(int uid){
        SqlSession session = DBController.getSession();
        RelationshipMapper mapper = session.getMapper(RelationshipMapper.class);
        try {
            Vector<Relationship> rs = mapper.selectRelationByid(uid);
            session.commit();
            return rs;
        } catch (Exception e){
            session.rollback();
            return null;
        }
    }
    public static Vector<Relationship> SelectFriendRelationship(int frienduid){
        SqlSession session = DBController.getSession();
        RelationshipMapper mapper = session.getMapper(RelationshipMapper.class);
        try {
            Vector<Relationship> rs = mapper.selectFriendRelationship(frienduid);
            session.commit();
            return rs;
        } catch (Exception e){
            session.rollback();
            return null;
        }
    }

    public static void InsertRelation(int uid,int frienduid){
        SqlSession session = DBController.getSession();
        RelationshipMapper mapper = session.getMapper(RelationshipMapper.class);
        Relationship relationship = SelectRelationship(uid,frienduid);
        Relationship relationship1 = SelectRelationship(frienduid,uid);
        if(relationship == null && relationship1 == null) {
            try {
                mapper.insertRelation(uid,frienduid);
                session.commit();
            } catch (Exception e) {
                session.rollback();
            }
        }
    }

    public static void DeleteRelation(int uid,int frienduid){
        SqlSession session = DBController.getSession();
        RelationshipMapper mapper = session.getMapper(RelationshipMapper.class);
        try {
            mapper.deleteRelation(uid,frienduid);
            session.commit();
        } catch (Exception e){
            session.rollback();
        }
    }

}
