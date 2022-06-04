package connection;

import javax.jms.Connection;
import javax.jms.JMSException;

public interface IActiveMQConnection {
    Connection getConnection(String brokerUrl) throws JMSException;
}
