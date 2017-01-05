import helloworldServer.DataType.Infomation;
import helloworldServer.DataType.Message;
/**
 * Created by painter on 16-12-27.
 *
public class Control {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://mysql.rdsmxunwwqylipo.rds.bj.baidubce.com/ltdb";
    String userid = "sa";
    String password = "19970520";
    Connection con = null;

    public ResultSet executeQuery(String sqlstr) {
        try {
            if(con == null || con.isClosed()) {
                Class.forName(driver);
                con = DriverManager.getConnection(url, userid, password);
            }
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(sqlstr);
            //con.close();
            return rs;
        }catch (Exception e){
            return null;
        }
    }

    public int executeUpdate(String sqlstr) throws Exception{
        if(con == null || con.isClosed()) {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, password);
        }
        Statement sta = con.createStatement();
        int a = sta.executeUpdate(sqlstr);
        //con.close();
        return a;
    }

    public int VerificationUser(hw_user recuser) {
        String sqlstr = "select * from hw_user where userid = '"+recuser.userid.replace("'","")+"'";
        try{
            ResultSet rs = executeQuery(sqlstr);
            if(rs.next()) {
                String n_password = rs.getString("password");
                if (n_password.trim().equals(recuser.password.trim())) {
                    recuser.setuid(Integer.parseInt(rs.getString("uid")));
                    return 1;//正确
                }
                else
                    return 2;//密码错误
            }else
                return 0;
        }catch (Exception e){
            return 0;//没有用户
        }
    }//验证用户登录

    public void SaveMessage(Infomation n_info){
        n_info.content.replace("'","''");

        String sqlstr = "insert into hw_message values("+n_info.sender+","+n_info.recipient+",'"+n_info.content+"','"+Infomation.outputdate(n_info.senddate)+"');";
        try {
            executeUpdate(sqlstr);
        }catch (Exception e){}
    }//把未读信息缓存到数据库

    public Vector<Infomation> GetMessages(int recipient){
        String sqlstr = "select * from hw_message where recipient = "+recipient;
        ResultSet rs = executeQuery(sqlstr);

        sqlstr = "delete from hw_message where recipient = "+recipient;
        try{
            executeUpdate(sqlstr);
        }catch(Exception e){}
        Vector<Infomation> messages = new Vector<Infomation>();
        try {
            if (rs != null) {
                while (rs.next()) {
                    Infomation n_info = new Infomation(rs.getInt("sender"),rs.getInt("recipient"),rs.getString("content"),rs.getTimestamp("senddate"));
                    messages.add(n_info);
                }
            }
        }catch (Exception e){}
        return messages;

    }//获取一个人的未读消息列表
}
 */