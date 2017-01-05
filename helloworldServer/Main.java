package helloworldServer;

import helloworldServer.Module.Communication;
import helloworldServer.Module.LoginMoudle;
import helloworldServer.Share.Common;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by painter on 16-12-27.
 */

public class Main extends Thread{
    public static void main(String args[]) {
            Main mainThead = new Main();
            mainThead.start();
    }

    @Override
    public void run(){
        try {
            ServerSocket Linstensocket = new ServerSocket(19001);
            while (Common.openserver) {
                Socket Usersocket = Linstensocket.accept();
                if(Usersocket.isConnected()){
                    System.out.println(Usersocket.getInetAddress()+"已连接");
                    Communication communication = new Communication(Usersocket);
                    communication.IncreaseMoudle(new LoginMoudle(communication));
                    communication.start();
                }
            }
        }
        catch(IOException ioex) {
            System.out.println("服务器端口号已被占用");
        }
    }
}