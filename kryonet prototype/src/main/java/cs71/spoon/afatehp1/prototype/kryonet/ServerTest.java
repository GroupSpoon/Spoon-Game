package cs71.spoon.afatehp1.prototype.kryonet;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class ServerTest {
    public static void main(String[] args) throws IOException {
        Server server = new com.esotericsoftware.kryonet.Server();
        Kryo kryo = server.getKryo();
        kryo.register(ReqsResponses.HelloWorld.class);
        kryo.register(ReqsResponses.HelloBack.class);

        server.start();
        server.bind(54555, 54777);
        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof ReqsResponses.HelloWorld) {
                    ReqsResponses.HelloWorld request = (ReqsResponses.HelloWorld) object;
                    System.out.println(request.text);
                    ReqsResponses.HelloBack response = new ReqsResponses.HelloBack();
                    response.text = "hi, pal";
                    connection.sendTCP(response);
                }
            }
        });
    }

}
