package pl.edu.pw.zad3;

import pl.edu.pw.zad3.listener.ConsumerListener;
import pl.edu.pw.zad3.listener.FirstBufferListener;
import pl.edu.pw.zad3.listener.SecondBufferListener;
import pl.edu.pw.zad3.presentation.MainFrame;

public class Main {

    public static MainFrame frame;
    public static int BUF1SIZE = 15;
    public static int BUF2SIZE = 5;

    public static void main(String[] args) {

        FirstBufferListener firstBufferListener = new FirstBufferListener();
        SecondBufferListener secondBufferListener = new SecondBufferListener();
        ConsumerListener consumerListener = new ConsumerListener();

        FirstBuffer buffer1 = new FirstBuffer(BUF1SIZE);
        buffer1.addListener(firstBufferListener);

        SecondBuffer buffer2 = new SecondBuffer(BUF2SIZE);
        buffer2.addListener(secondBufferListener);

        frame = new MainFrame();

        Producer firstProducer = new Producer(buffer1, 500);
        Producer secondProducer = new Producer(buffer1, 300);

        Processor processor = new Processor(buffer1, buffer2);

        Consumer consumer = new Consumer(buffer2);
        consumer.addListener(consumerListener);

        new Thread(firstProducer).start();
        new Thread(secondProducer).start();
        new Thread(processor).start();
        new Thread(consumer).start();
    }

}