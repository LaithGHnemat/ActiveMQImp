package producer;

import connection.IActiveMQConnection;
import sender.IMessageSender;

import javax.jms.*;

public class Producer implements IProducer {

    private String brokerUrl;
    private String queueName;
    private IMessageSender messageSender;
    private IActiveMQConnection activeMQConnection;

    public Producer(String url, String queueName, IMessageSender messageSender, IActiveMQConnection activeMQConnection) {
        this.brokerUrl = url;
        this.queueName = queueName;
        this.messageSender = messageSender;
        this.activeMQConnection = activeMQConnection;
    }

    @Override
    public void run() {
        sendToQueue(brokerUrl, queueName);
    }

    public void sendToQueue(String url, String queueName) {
        while (true) {
            try {
                Connection connection = activeMQConnection.getConnection(url);
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination ourQueue = session.createQueue(queueName);
                MessageProducer producer = session.createProducer(ourQueue);
                messageSender.sendMessage(session, producer);
                close(connection, session);
            } catch (JMSException | InterruptedException ignore) {
            }
        }
    }
    private void close(Connection connection, Session session) throws JMSException {
        session.close();
        connection.close();
    }
}
