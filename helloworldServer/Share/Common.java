package helloworldServer.Share;

import helloworldServer.DataType.Message;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by painter on 16-12-27.
 */
public class Common {

    //static public Control DataBaseOperator = new Control();//操作数据库的类

    static public Hashtable Usertable = new Hashtable();
    //显示哈希表内容
    static public void ShowHash(){
        Enumeration e = Usertable.keys();
        if(e.hasMoreElements()) {
            System.out.println("在线用户:");
        }else{
            System.out.println("无在线用户");}
        while (e.hasMoreElements()){
            int key = (int)e.nextElement();
            System.out.println("用户"+key);
        }
    }

    static public boolean openserver = true;


    //通用组件接口
    public static int IONMOUDLE = 0;
    public static int BEANNMOUDLE = 1;
    public static int MESSAGEMOUDLE = 2;
    public static int LOGINMOUDLE = 3;
    public static int LOGOUTMOUDLE = 4;
    public static int LOADMOUDLE = 5;

    //XML转换成类
    public static Object GetObject(String xmlStr,Class... classesToBeBound ) {
        try {
            JAXBContext context = JAXBContext.newInstance(classesToBeBound);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(new StringReader(xmlStr));
        }catch (JAXBException e){
            e.printStackTrace();
            return null;
        }
    }

    //类转换成XML
    public static String GetXml(Object type){
        try{
            JAXBContext context = JAXBContext.newInstance(type.getClass());
            Marshaller marshaller = context.createMarshaller();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            marshaller.marshal(type,os);
            return os.toString();
        }catch (Exception e){
            return null;
        }
    }
}
