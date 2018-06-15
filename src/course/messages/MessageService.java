package course.messages;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.ejb.Stateless;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

@Stateless
public class MessageService {
    private Connection connection;

    public MessageService() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String message) {
        try {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("logs", "fanout");
            channel.basicPublish("logs", "", null, message.getBytes("UTF-8"));
            channel.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public String createQueue() {
        String queueName = "";
        try {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("logs", "fanout");
            String queue = "";
            try {
                Random random = new Random();
                String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
                while (true) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 12; i++) {
                        sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
                    }
                    queue = sb.toString();
                    channel.queueDeclarePassive(queue);
                }
            } catch (IOException e) {
                channel = connection.createChannel();
                queueName = channel.queueDeclare(queue, false, false, true, null).getQueue();
                channel.queueBind(queueName, "logs", "");
                channel.close();
            }
        } catch (IOException | TimeoutException e) {
            System.out.println("Couldn't connect");
        }
        return queueName;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            connection.close();
        } finally {
            super.finalize();
        }
    }
}
