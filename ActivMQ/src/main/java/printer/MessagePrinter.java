package printer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class MessagePrinter implements IMessagePrinter {
    public void printMessage(Message message) throws JMSException {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println("Consumer Received: " + text);
        }
    }
}
