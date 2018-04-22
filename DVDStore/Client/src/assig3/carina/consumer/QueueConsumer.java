package assig3.carina.consumer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import common.DVD;
import common.EndPoint;

public class QueueConsumer extends EndPoint implements Runnable, Consumer{
	
	public QueueConsumer(String endPointName) throws IOException, TimeoutException{
		super(endPointName);		
	}
	
	public boolean getDVD(String dvd){
		boolean gotIt = false;
		
		List<String> lines = Arrays.asList(dvd, "\n");
		Path file = Paths.get("DVD.txt");
		
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
			gotIt = true;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return gotIt;
		
	}
	public void run() {
		try {
			//start consuming messages. Auto acknowledge messages.
			channel.basicConsume(endPointName, true,this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Called when consumer is registered.
	 */
	public void handleConsumeOk(String consumerTag) {
		System.out.println("Consumer "+consumerTag +" registered");		
	}

	/**
	 * Called when new message is available.
	 */
	public void handleDelivery(String consumerTag, Envelope env,
			BasicProperties props, byte[] body) throws IOException {
		Map map = (HashMap)SerializationUtils.deserialize(body);
	//	getDVD(body.toString());
	    System.out.println("Message received.");
		
	}

	public void handleCancel(String consumerTag) {}
	public void handleCancelOk(String consumerTag) {}
	public void handleRecoverOk(String consumerTag) {}
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}

	

}
