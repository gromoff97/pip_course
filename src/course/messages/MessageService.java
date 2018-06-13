package course.messages;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.ejb.Stateless;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Stateless
public class MessageService {
    private Connection connection;
    private Channel channel;

    public MessageService() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String message) {
        try {
            channel.exchangeDeclare("logs", "fanout");
            channel.basicPublish("logs", "", null, message.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createQueue() {
        String queueName = "";
        try {
            channel.exchangeDeclare("logs", "fanout");
            queueName = channel.queueDeclare("", false, false, true, null).getQueue();
            channel.queueBind(queueName, "logs", "");
        } catch (IOException e) {
            System.out.println("Couldn't connect.");
        }
        return queueName;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            channel.close();
            connection.close();
        } finally {
            super.finalize();
        }
    }
}
