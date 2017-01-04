package helloworld;

import helloworld.Share.Common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayOutputStream;

/**
 * Created by painter on 17-1-4.
 */
public class Test {
    public static void main(String[] args){
        Datagram a = new Datagram(10,"hhh");
        String b = Common.GetXml(a);
        System.out.printf(b);
    }
}

@XmlRootElement
class Datagram{
    public int Port;
    public String Content;

    public Datagram(){
        super();
    }
    public Datagram(int port,String content){
        this.Port = port;
        this.Content = content;
    }
}

