package consumer;

import connection.IActiveMQConnection;
import printer.IMessagePrinter;

import javax.jms.*;

public class Consumer implements IConsumer {

    private String brokerUrl;
    private String queueName;
    private IMessagePrinter messagePrinter;
    private IActiveMQConnection activeMQConnection;


    public Consumer(String brokerUrl, String queueName, IMessagePrinter messagePrinter,
                    IActiveMQConnection activeMQConnection) {
        this.brokerUrl = brokerUrl;
        this.queueName = queueName;
        this.messagePrinter = messagePrinter;
        this.activeMQConnection = activeMQConnection;
    }

    @Override
    public void run() {
        readQueue(brokerUrl, queueName);
    }

    public void readQueue(String brokerUrl, String queueName) {
        try {
            while (true) {
                Connection connection = activeMQConnection.getConnection(brokerUrl);
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination queue = session.createQueue(queueName);
                MessageConsumer consumer = session.createConsumer(queue);
                Message message = consumer.receive(1000);
                messagePrinter.printMessage(message);
                checkMessage(message);
                close(connection, session);
            }
        } catch (JMSException | InterruptedException ignore) {
        }
    }

    private void checkMessage(Message message) throws InterruptedException {
        if (message == null) {
            Thread.sleep(5000);
        }
    }

    private void close(Connection connection, Session session) throws JMSException {
        session.close();connection.close();
    }
}
