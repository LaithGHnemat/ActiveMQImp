package consumer;

public interface IConsumer extends Runnable{
    void readQueue(  String brokerUrl, String queueName);
}
