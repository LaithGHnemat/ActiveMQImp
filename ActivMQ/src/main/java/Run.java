import connection.ActiveMQConnection;
import connection.IActiveMQConnection;
import consumer.Consumer;
import consumer.IConsumer;
import printer.IMessagePrinter;
import printer.MessagePrinter;
import producer.Producer;
import sender.IMessageSender;
import sender.MessageSender;

public class Run {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        IActiveMQConnection activeMQConnection= new ActiveMQConnection();
        IMessageSender messageSender= new MessageSender();
        IMessagePrinter messagePrinter= new MessagePrinter();
        startProduser(activeMQConnection, messageSender);
        startConsumer(activeMQConnection, messagePrinter);
    }

    private static void startConsumer(IActiveMQConnection activeMQConnection, IMessagePrinter messagePrinter) {
        IConsumer consumer = new Consumer( "tcp://localhost:61616","LaithQueue",
                messagePrinter, activeMQConnection);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }

    private static void startProduser(IActiveMQConnection activeMQConnection, IMessageSender messageSender) {
        Producer producer=new Producer("tcp://localhost:61616","LaithQueue", messageSender, activeMQConnection);
        Thread producerThread = new Thread(producer);
        producerThread.start();
    }
}
