package producer;

public interface IProducer extends Runnable{
    void sendToQueue(String url,String queueName);
}
