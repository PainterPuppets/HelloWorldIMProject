package helloworldServer.DataType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by painter on 16-12-29.
 */

@XmlRootElement
public class Infomation {
    public int sender;//发信人
    public int recipient;//收信人
    public String  content;//内容
    public Date senddate;//发信时间

    public Infomation(int n_sender,int n_recipient,String n_content,Date n_senddate){
        this.content = n_content;
        this.recipient = n_recipient;
        this.sender = n_sender;
        this.senddate = n_senddate;
    }
    public Infomation(int n_sender,int n_recipient,String n_content){
        this.content = n_content;
        this.recipient = n_recipient;
        this.sender = n_sender;
        this.senddate = new Date();
    }
    public Infomation(){
        super();
    }


    public String GetXmlStr(){
        try {
            JAXBContext context = JAXBContext.newInstance(Infomation.class);
            Marshaller marshaller = context.createMarshaller();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            marshaller.marshal(this, os);
            return os.toString();
        }catch (Exception e) {
            return null;
        }
    }

    public static Infomation GetInfofromStr(String xmlStr) {
        try {
            JAXBContext context = JAXBContext.newInstance(Infomation.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Infomation) unmarshaller.unmarshal(new StringReader(xmlStr));
        }catch (JAXBException e){
            return null;
        }
    }

    public static String outputdate(Date n_date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(n_date);// new Date()为获取当前系统时间
    }//格式化输出date类

}
