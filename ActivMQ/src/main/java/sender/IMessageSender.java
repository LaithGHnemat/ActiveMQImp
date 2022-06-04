package sender;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

public interface IMessageSender  {
    void sendMessage(Session session, MessageProducer producer) throws JMSException, InterruptedException;
}
