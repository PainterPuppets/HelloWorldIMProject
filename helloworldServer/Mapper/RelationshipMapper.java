package helloworldServer.Mapper;

import helloworldServer.Model.Relationship;
import org.apache.ibatis.annotations.Param;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Vector;

/**
 * Created by painter on 17-1-4.
 */
@XmlRootElement
public interface RelationshipMapper {
    public int insertRelation(@Param(value="uid")int uid,@Param(value="frienduid")int frienduid) throws Exception;

    public Relationship selectRelation(@Param(value="uid")int uid,@Param(value="frienduid")int frienduid) throws Exception;

    public Vector<Relationship> selectRelationByid(int uid) throws Exception;

    public Vector<Relationship> selectFriendRelationship(int frienduid)throws Exception;

    public int deleteRelation(int uid,int frienduid)throws Exception;
}
