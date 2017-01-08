package helloworld.Module;

import helloworld.Share.Common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by painter on 17-1-2.
 */
public class BeanMoudle implements SuperMoudle{
    int Port = Common.BEANNMOUDLE;
    Communication communication;
    int LastBean = 0;

    public BeanMoudle(){
        this(Common.currentcommunication);
    }
    public BeanMoudle(Communication com){
        this.communication = com;
        System.out.println("bean模块已加载");
        (new BeanThrean()).start();
    }

    @Override
    public void Receive(String Msg) {
        LastBean = 0;
    }

    @Override
    public int GetPort() {
        return Port;
    }

    class BeanThrean extends Thread{
        @Override
        public void run() {
            while (communication.LinkFlag){
                if(LastBean < 40) {
                    if(LastBean >30)
                        communication.SendDatagram(GetPort(),"");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    LastBean++;
                }else {
                    Common.mainFormController.Logout();
                }
            }
        }
    }//心跳数据线程
}
