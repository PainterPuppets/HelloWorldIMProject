package helloworld.DataType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-4.
 */
@XmlRootElement
public class Logingram{
    public String Userid;
    public String Password;

    public Logingram(){
        super();
    }
    public Logingram(String userid,String password){
        this.Password = password;
        this.Userid = userid;
    }
}
