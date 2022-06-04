package printer;

import javax.jms.JMSException;
import javax.jms.Message;

public interface IMessagePrinter {
    void printMessage(Message message) throws JMSException;
}
