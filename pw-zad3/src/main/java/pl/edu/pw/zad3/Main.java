package pl.edu.pw.zad3;

import pl.edu.pw.zad3.listener.ConsumerListener;
import pl.edu.pw.zad3.listener.FirstBufferListener;
import pl.edu.pw.zad3.listener.SecondBufferListener;
import pl.edu.pw.zad3.presentation.MainFrame;
import pl.edu.pw.zad3.runnable.Consumer;
import pl.edu.pw.zad3.runnable.Processor;
import pl.edu.pw.zad3.runnable.Producer;

public class Main {

    public static MainFrame frame;
    public static int BUF1SIZE = 15;
    public static int BUF2SIZE = 5;

    public static void main(final String[] args) {

        FirstBufferListener firstBufferListener = new FirstBufferListener();
        SecondBufferListener secondBufferListener = new SecondBufferListener();
        ConsumerListener consumerListener = new ConsumerListener();

        FirstBuffer firstBuffer = new FirstBuffer(BUF1SIZE);
        firstBuffer.addListener(firstBufferListener);

        SecondBuffer secondBuffer = new SecondBuffer(BUF2SIZE);
        secondBuffer.addListener(secondBufferListener);

        frame = new MainFrame();

        Producer firstProducer = new Producer(firstBuffer, 500);
        Producer secondProducer = new Producer(firstBuffer, 300);

        Processor processor = new Processor(firstBuffer, secondBuffer);

        Consumer consumer = new Consumer(secondBuffer);
        consumer.addListener(consumerListener);

        new Thread(firstProducer).start();
        new Thread(secondProducer).start();
        new Thread(processor).start();
        new Thread(consumer).start();
    }

}