import helloworldServer.DataType.Infomation;
import helloworldServer.DataType.Message;

/**
 * Created by painter on 16-12-28.
 * 实现和客户端交互的类
 *
public class Client extends Thread {

    int uid = 0;
    Socket Usersocket;
    boolean linkflag;
    OutputStream os;
    BufferedReader br;

    int LastBeanTime = 0;
    BeanThread beanthread = new BeanThread();

    @Override
    public void run(){
        try {
            String recstr;
            while (linkflag && (recstr = br.readLine())!= null) {
                System.out.println("接收到来自"+this.Usersocket.getInetAddress()+"的消息:"+recstr);
                excmd(recstr);
            }
        }catch (Exception e){
        }
    }

    public Client(Socket n_Socket){
        try {
            this.Usersocket = n_Socket;
            this.br = new BufferedReader(new InputStreamReader(this.Usersocket.getInputStream()));
            this.os = Usersocket.getOutputStream();
            this.linkflag = true;
        }catch (Exception e){
            System.out.println("创建"+this.Usersocket.getInetAddress()+"的Client对象失败");
        }

    }//初始化

    public void sendmsg(Message sendmsg){
        try {
            String sendstr = sendmsg.GetXmlStr()+'\n';
            System.out.println("服务器发送的数据为:"+sendstr);
            this.os.write(sendstr.getBytes());
        }catch (Exception e){
            System.out.println(uid+"发送信息错误,请检查代码");
        }
    }//发送信息

    private void excmd(String cmdstr){
        Message recmsg = Message.GetMsgfromStr(cmdstr);
        if((recmsg.type == 3 || IsLogin())&&recmsg != null){
            switch (recmsg.type) {
                case 0:
                    exerrorcmd(recmsg);
                    break;
                case 1://心跳数据包
                    Bean();
                    break;
                case 2://
                    exmessagingcmd(recmsg);
                    break;
                case 3://登录请求
                    System.out.println("正在处理登录请求");
                    login((hw_user) Common.XmltoObject(new hw_user(),recmsg.context));
                    break;
                default:
                    break;
            }
        }else if(!IsLogin()){//未登录
            sendmsg(Common.Error001);
        }
    }//执行命令

    private void exerrorcmd(Message cmdmsg){
        switch (cmdmsg.param){
            case 0://用户退出
                logout();
                break;
            default:
                break;
        }
    }//执行错误命令

    private void exmessagingcmd(Message cmdmsg){
        switch(cmdmsg.param) {
            case 0://普通信息
                Infomation currentInfo = Infomation.GetInfofromStr(cmdmsg.context);
                if (Common.Usertable.containsKey(currentInfo.recipient)) {
                    ((Client) Common.Usertable.get(currentInfo.recipient)).NewMessaging(currentInfo);
                } else
                    Common.DataBaseOperator.SaveMessage(currentInfo);
                break;
            default:
                break;
        }
    }//执行通讯命令

    public void NewMessaging(Infomation n_info){
        if(this.linkflag && this.IsLogin()){
            sendmsg(new Message(2,0,n_info.GetXmlStr()));
        }else{
            Common.DataBaseOperator.SaveMessage(n_info);
        }
    }//接收新消息

    private boolean IsLogin(){
        if(uid != 0)
            if(Common.Usertable.containsKey(uid)&&Common.Usertable.contains(this))
                return true;
            else
                return false;
        else return false;
    }//是否登录

    public void login(hw_user loginuser){
        switch(Common.DataBaseOperator.VerificationUser(loginuser)) {
            case 0:
                sendmsg(Common.LoginMsgfail);
                this.Close();
                break;
            case 1:
                loging(loginuser);
            break;
            case 2:
                sendmsg(Common.LoginMsgWrong);
                this.Close();
                break;
        }
    }//接收登录请求

    private void loging(hw_user loginuser){
        this.uid = loginuser.uid;
        if (Common.Usertable.containsKey(uid)) {
            System.out.println("用户"+uid+"已重复登录");
            ((Client) Common.Usertable.get(uid)).sendmsg(new Message(0,2,""));
            ((Client) Common.Usertable.get(uid)).Close();
        }
        Common.Usertable.put(uid, this);
        System.out.println("用户"+uid + "已连接服务器");
        beanthread.start();
        Common.ShowHash();
        sendmsg(new Message(3,1,loginuser.GetXmlStr()));

    }//登录成功

    public void logout(){
        if(IsLogin()){
            this.sendmsg(Common.LogoutMsg);
            this.Close();
        }else
            this.Close();
    }//登出

    public void Close(){
        try {
            if(IsLogin()) {
                System.out.println(this.uid+"已下线");
                Common.Usertable.remove(this.uid);
                Common.ShowHash();
            }
            this.Usersocket.close();
            this.linkflag = false;
        }catch (IOException e){}
    }//关闭连接

    private void Bean(){
        this.LastBeanTime = 0;
        sendmsg(Common.BeanMsg);
    }//接收心跳数据包

    class BeanThread extends Thread{
        @Override
        public void run(){
            try {
                while (linkflag) {
                    if (LastBeanTime > 45) {
                        Close();
                    } else
                        LastBeanTime++;
                    Thread.sleep(1000);
                }
            }catch (Exception e){
                System.out.println("心跳线程异常");
                e.printStackTrace();
            }
        }
    }
}
*/