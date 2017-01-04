package helloworld.Share;

import helloworld.Controller.LoginFormController;
import helloworld.Controller.MainFormController;
import helloworld.Form.Main;
import helloworld.Module.Communication;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.Hashtable;

/**
 * Created by painter on 16-12-28.
 */
public class Common {
    public static boolean Connectserver = false;
    public static Communication currentcommunication = null;



    public static Main main = null;
    //Controller
    public static Hashtable ChatFormTable;
    public static LoginFormController loginFormController = null;
    public static MainFormController mainFormController = null;


    //通用组件接口
    public static int IONMOUDLE = 0;
    public static int BEANNMOUDLE = 1;
    public static int MESSAGEMOUDLE = 2;
    public static int LOGINMOUDLE = 3;
    public static int LOGOUTMOUDLE = 4;



    //XML转换成类
    public static Object GetObject(String xmlStr,Class... classesToBeBound ) {
        try {
            JAXBContext context = JAXBContext.newInstance(classesToBeBound);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(new StringReader(xmlStr));
        }catch (JAXBException e){
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
            e.printStackTrace();
            return null;
        }
    }

}
