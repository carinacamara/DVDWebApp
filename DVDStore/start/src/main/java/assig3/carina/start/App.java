package assig3.carina.start;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import assig3.carina.consumer.QueueConsumer;
import assig3.carina.producer.Producer;
import common.DVD;

/**
 * Hello world!
 *
 */
public class App 
{
public App() throws Exception{
		
		QueueConsumer consumer = new QueueConsumer("queue");
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		
		Producer producer = new Producer("queue");
		
		for (int i = 0; i < 100000; i++) {
			
			HashMap message = new HashMap<String,Integer>();
			
			DVD dvd = new DVD();
			// create dvd with paramsffrom web
			
			// trebuie sa modificam proiectul la unul web
			
			// clasa asta este controller ul care primeste dvd de la interfata
			
			message.put(dvd.toString(), i);
			
			producer.sendMessage(message);
			
			System.out.println("Message Number "+ i +" sent.");
		}
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception{
	  new App();
	}
}
