package helloworldServer.Module;
import helloworldServer.Model.User;
import helloworldServer.Share.*;
import helloworldServer.DataType.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.net.Socket;
import java.util.Hashtable;

/**
 * Created by painter on 16-12-28.
 */
public class Communication extends Thread{

    private Socket TcpSocket;//链接的Socket
    private boolean LinkFlag = false;//连接状态
    private User user;//连接人
    private BufferedWriter Writer;//用于传输数据
    private BufferedReader Reader;//用于接收数据
    public IOMoudle Io = new IOMoudle();//用于处理IO问题

    private Hashtable Slot = new Hashtable();


    @Override
    public void run(){
        try{
            String recstr;
            while (LinkFlag && (recstr = Reader.readLine())!= null) {
                System.out.println("接收到服务器的消息:"+recstr);
                Distribution(recstr);
            }
        }catch (Exception e){}
    }//循环监听信息

    public Communication(Socket linkSocket){
        try {
            this.TcpSocket = linkSocket;
            this.Reader = new BufferedReader(new InputStreamReader(this.TcpSocket.getInputStream()));
            this.Writer = new BufferedWriter(new OutputStreamWriter(TcpSocket.getOutputStream()));
            this.LinkFlag = true;
            IncreaseMoudle(this.Io);//添加IO组件
        }catch (Exception e){
            System.out.println("创建TcpClient对象失败");
        }
    }//初始化

    private void Distribution(String recstr){
        Datagram n_datagram = (Datagram) Common.GetObject(recstr,Datagram.class);
        if(Slot.containsKey(n_datagram.Port)){
            ((SuperMoudle)Slot.get(n_datagram.Port)).Receive(n_datagram.Content);
        }
    }//分发数据包

    public Boolean SendDatagram(int port,String content){
        try {
            Datagram datagram = new Datagram(port,content);
            String msg = Common.GetXml(datagram);
            Writer.write(msg+'\n');
            System.out.println("已发送"+msg);
            Writer.flush();
            return true;
        }catch (Exception e){
            return false;
        }
    }//发送数据报

    public boolean IncreaseMoudle(SuperMoudle addMoudle){
        if(!Slot.containsKey(addMoudle.GetPort())) {
            Slot.put(addMoudle.GetPort(), addMoudle);
            return true;
        }
        else return false;
    }//增加模组

    public boolean DeleteMoudle(int delPort){
        if(Slot.containsKey(delPort)){
            Slot.remove(delPort);
            return true;
        }else return false;
    }//卸载指定端口的组件

    public Object GetMoudle(int getPort){
        if(Slot.containsKey(getPort)){
            return Slot.get(getPort);
        }else
            return null;
    }//获取指定端口的组件

    public void SetUser(User user){
        this.user = user;
    }
    public User GetUser(){
        return this.user;
    }

    public void Close(){
        this.LinkFlag = false;
        try {
            this.Reader.close();
            this.Writer.close();
            this.TcpSocket.close();
        }catch (IOException ioe){}
    }//关闭链接

    class IOMoudle implements SuperMoudle{
        @Override
        public int GetPort() {
            return 0;
        }

        @Override
        public void Receive(String Msg) {

        }
        
    }

    /*


    private int LastBeanTime = 0;//最后一次心跳包额时间
    BeanTread bt= new BeanTread();//心跳线程






    private void exerrorcmd(Message cmdmsg){
        switch (cmdmsg.param){
            case 0://退出信息
                logout();
                break;
            case 1://未登录
                Error01();
                break;
            case 2://异地登录
                Error02();
                break;
            default:
                break;
        }
    }//执行错误命令

    private void exmessagingcmd(Message cmdmsg){
        switch (cmdmsg.param){
            case 0://普通信息
                recmessaging(ComMessage.GetInfofromStr(cmdmsg.context));
                break;
            default:
                break;
        }
    }//执行通信命令

    private void recmessaging(ComMessage n_info){
        System.out.println("接收到来自"+n_info.sender+"的消息:"+n_info.content.replace("''","'")+"("+ ComMessage.outputdate(n_info.senddate)+")");
    }//接收通信信息

    //001错误:未登录
    public void Error01(){
        logout();
    }

    //002错误:重复登录
    private void Error02(){
        System.out.println("账号已经在其他地点登陆");
        logout();
    }

    //发送数据包
    public void SendMsg(Message sendmsg){
        try {
            String sendstr = sendmsg.GetXmlStr()+'\n';
            //System.out.println("向服务器发送的数据为:"+sendstr);
            this.os.write(sendstr.getBytes());
        }catch (Exception e){
            //System.out.println("信息错误,请检查代码,可能是网络原因");
            logout();
        }
    }

    private void Reclogin(Message recmsg){
        switch (recmsg.param){
            case 0://账号不存在
                helloworldServer.Share.Common.LoginController.Loginerror();
                break;
            case 1://登录成功
                loging(recmsg);
                break;
            case 2://密码错误
                helloworldServer.Share.Common.LoginController.Loginfail();
                break;
            default:
                break;
        }
    }//接收登录结果

    private void loging(Message recmsg){
        this.currentuser = hw_user.GetuserfromStr(recmsg.context);
        helloworldServer.Share.Common.LoginController.Loginpass();
        OnLine = true;
        bt.start();
    }//登录成功

    public void Login(String userid,String password){
        hw_user loginuser = new hw_user(userid,password);
        Message logmsg = new Message(3,helloworldServer.Share.Common.GetXml(loginuser));
        SendMsg(logmsg);
    }//发送登录请求


    public void SendlogoutMsg(){
        SendMsg(helloworldServer.Share.Common.LogoutMsg);
    }
    public void logout(){
        System.out.println("已离线");
        this.OnLine = false;
        this.LinkFlag = false;
        try{
            this.os.close();
            this.br.close();
            this.TcpSocket.close();
            helloworldServer.Share.Common.currentcommuniation = null;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void Bean(){
        System.out.println("发送心跳数据包");
        this.LastBeanTime = 0;
    }

    class BeanTread extends Thread {
        @Override
        public void run(){
            try{
                while(OnLine){
                    if (LastBeanTime > 30 && LastBeanTime < 45)
                        SendMsg(helloworldServer.Share.Common.BeanMsg);
                    if (LastBeanTime > 45)
                        SendlogoutMsg();
                    LastBeanTime++;
                    Thread.sleep(1000);
                }
            }catch (Exception e){
                System.out.println("心跳线程异常");
                e.printStackTrace();
            }
        }
    }*/
}
