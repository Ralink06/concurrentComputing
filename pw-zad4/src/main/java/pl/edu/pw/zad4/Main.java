package pl.edu.pw.zad4;

import lombok.extern.slf4j.Slf4j;
import pl.edu.pw.zad4.buffer.FirstBuffer;
import pl.edu.pw.zad4.buffer.SecondBuffer;
import pl.edu.pw.zad4.runnable.Consumer;
import pl.edu.pw.zad4.runnable.Producer;
import pl.edu.pw.zad4.runnable.Taker;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Main {
    private static final int SECOND_BUFFER_SIZE = 5;
    private static final int[] SLEEP_TIMES = new int[]{500, 750, 1000, 1250, 1500};

    public static void main(final String[] args) {
        FirstBuffer firstBuffer = new FirstBuffer();
        SecondBuffer secondBuffer = new SecondBuffer(SECOND_BUFFER_SIZE);

        List<Producer> producers = createProducers(2, firstBuffer);
        Taker taker = new Taker(firstBuffer, secondBuffer, getRandomSleepTimes());
        Consumer consumer = new Consumer(secondBuffer, getRandomSleepTimes());

        new Thread(firstBuffer).start();
        new Thread(secondBuffer).start();
        producers.forEach(producer -> new Thread(producer).start());
        new Thread(taker).start();
        new Thread(consumer).start();
    }

    private static List<Producer> createProducers(final int numberOfProducers, final FirstBuffer firstBuffer) {
        return IntStream.rangeClosed(1, numberOfProducers)
                .mapToObj(i -> new Producer(firstBuffer, getRandomSleepTimes(), i))
                .collect(Collectors.toList());
    }

    private static int getRandomSleepTimes() {
        int random = new Random().nextInt(SLEEP_TIMES.length);
        return SLEEP_TIMES[random];
    }

}