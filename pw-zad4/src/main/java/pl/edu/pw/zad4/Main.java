package pl.edu.pw.zad4;

import lombok.extern.slf4j.Slf4j;
import pl.edu.pw.zad4.buffer.FirstBuffer;
import pl.edu.pw.zad4.buffer.SecondBuffer;
import pl.edu.pw.zad4.runnable.Consumer;
import pl.edu.pw.zad4.runnable.Producer;
import pl.edu.pw.zad4.runnable.Taker;

@Slf4j
public class Main {
    public static int SECOND_BUFFER_SIZE = 8;

    public static void main(final String[] args) {
        FirstBuffer firstBuffer = new FirstBuffer();
        SecondBuffer secondBuffer = new SecondBuffer(SECOND_BUFFER_SIZE);

        Producer producer = new Producer(firstBuffer, 1000, 1);
        Producer secondProducer = new Producer(firstBuffer, 500, 2);
        Taker taker = new Taker(firstBuffer, secondBuffer, 1000);
        Consumer consumer = new Consumer(secondBuffer);

        new Thread(firstBuffer).start();
        new Thread(secondBuffer).start();
        new Thread(producer).start();
        new Thread(secondProducer).start();
        new Thread(taker).start();
        new Thread(consumer).start();
    }

}