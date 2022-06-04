package sender;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Scanner;

public class MessageSender implements IMessageSender {

    public void sendMessage(Session session, MessageProducer producer) throws JMSException, InterruptedException {
        Thread.sleep(4000);
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the message that you wanna send please");
        TextMessage message = session.createTextMessage(scanner.next());
        producer.send(message);
        System.out.println("Message '" + message.getText() + ",it has been sent successfully to the Queue");
    }
}
