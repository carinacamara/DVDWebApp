package assig3.carina.producer;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;

import common.DVD;
import common.DVDService;
import common.EndPoint;

/**
 * Hello world!
 *
 */
public class Producer extends EndPoint
{	

	
	public Producer(String endPointName) throws IOException, TimeoutException{
		super(endPointName);

		
		
	}

	public void sendMessage(Serializable object) throws IOException {
		
	    channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
	
	}	
	
	
	
	public void sendEmail(DVD DVD){
		
		MailService mailService = new MailService("xd.carina95@gmail.com","test*");
		mailService.sendMail("xd.carina@yahoo.ro","Dummy Mail Title", DVD.toString());
	}
	
	
}
