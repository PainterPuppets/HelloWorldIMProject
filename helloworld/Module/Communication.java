package helloworld.Module;

import helloworld.DataType.Datagram;
import helloworld.DataType.UserInfo;
import helloworld.Share.Common;

import java.io.*;
import java.net.Socket;
import java.util.Hashtable;

/**
 * Created by painter on 16-12-28.
 */
public class Communication extends Thread{
    private Socket TcpSocket;//链接的Socket
    public boolean LinkFlag = false;//连接状态
    public UserInfo currentuser = null;//当前登录用户
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
                new Excute(recstr).start();
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
    }//分配

    public Boolean SendDatagram(int port,String content){
        try {
            Datagram datagram = new Datagram(port,content);
            String msg = Common.GetXml(datagram);
            Writer.write(msg+'\n');
            Writer.flush();
            return true;
        }catch (Exception e){
            e.printStackTrace();
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

    public void Close(){
        System.out.println("communication关闭中");
        this.LinkFlag = false;
        try {
            this.TcpSocket.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
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

    class Excute extends Thread{

        String msgstr;

        @Override
        public void run(){
            Distribution(msgstr);
        }//循环监听信息

        public Excute(String MsgStr){
            this.msgstr = MsgStr;
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



    public void SendlogoutMsg(){
        SendMsg(Common.LogoutMsg);
    }
    public void logout(){
        System.out.println("已离线");
        this.OnLine = false;
        this.LinkFlag = false;
        try{
            this.os.close();
            this.br.close();
            this.TcpSocket.close();
            Common.currentcommuniation = null;
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
                        SendMsg(Common.BeanMsg);
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
