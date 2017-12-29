package cs71.spoon.afatehp1.prototype.kryonet;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class ClientTest {
    public static void main(String[] args) throws IOException{
        Client client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(ReqsResponses.HelloWorld.class);
        kryo.register(ReqsResponses.HelloBack.class);
        //client.start();
        Thread clientThread = new Thread(client);
        clientThread.start();
        client.connect(5000, "130.58.68.108", 54555, 54777);
        client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof ReqsResponses.HelloBack) {
                    ReqsResponses.HelloBack response = (ReqsResponses.HelloBack) object;
                    System.out.println(response.text);
                }
            }
        });
        ReqsResponses.HelloWorld request = new ReqsResponses.HelloWorld();
        request.text = "Hello, world.";
        client.sendTCP(request);
    }
}
