package poli.sem7.pw.zad5;

import java.util.concurrent.Semaphore;


public class Resource {
	Semaphore writingNowNumberSemaphore;//w2 - dla licznika pisarzy
	Semaphore readingNowCounterSemaphore;//w1 - dla licznika czytelników
	Semaphore blockReaderSemaphore;//semafor blokujący readera //priorytet pisarzy
	Semaphore sharedDataSemaphore;//semafor sunchornizujacy dostep do zasobu (strony)
	StringBuffer buffer;
	
	int readingNowNumber;  //liczba aktualnie czytajacych
	int writingNowNumber;	//liczba procesów które chcą pisać
	
	public Resource(String content){
		readingNowNumber = 0;
		writingNowNumber = 0;
		writingNowNumberSemaphore = new Semaphore(1, true);
		readingNowCounterSemaphore = new Semaphore(1, true);
		blockReaderSemaphore = new Semaphore(1, true);
		sharedDataSemaphore = new Semaphore(1, true);
		buffer = new StringBuffer(content);
	}
	
	void openForRead(){
		blockReaderSemaphore.acquireUninterruptibly();
			readingNowCounterSemaphore.acquireUninterruptibly();
				readingNowNumber++;
				if(readingNowNumber ==1) sharedDataSemaphore.acquireUninterruptibly();
			readingNowCounterSemaphore.release();
		blockReaderSemaphore.release();
	}
	
	void closeForRead(){
		readingNowCounterSemaphore.acquireUninterruptibly();
			readingNowNumber--;
			if(readingNowNumber ==0) sharedDataSemaphore.release();
		readingNowCounterSemaphore.release();
	}
	
	void openForWrite(){
		writingNowNumberSemaphore.acquireUninterruptibly();
			writingNowNumber++;
			if(writingNowNumber ==1) blockReaderSemaphore.acquireUninterruptibly();
		writingNowNumberSemaphore.release();
		sharedDataSemaphore.acquireUninterruptibly();
	}
	
	void closeForWrite(){
		sharedDataSemaphore.release();
		writingNowNumberSemaphore.acquireUninterruptibly();
			writingNowNumber--;
			if(writingNowNumber ==0) blockReaderSemaphore.release();
		writingNowNumberSemaphore.release();
	}
	
	int read(int pos){
		if(pos>=buffer.length())return -1;
		return buffer.charAt(pos);
	}
	
	void write(char c){
		buffer.append(c);
	}
	
	void clear(){
		buffer = new StringBuffer();
	}
}
