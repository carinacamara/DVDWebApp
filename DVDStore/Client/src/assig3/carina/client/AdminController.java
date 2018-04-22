package assig3.carina.client;

import java.io.IOException;
import java.util.HashMap;

import java.util.concurrent.TimeoutException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import assig3.carina.consumer.QueueConsumer;
import assig3.carina.producer.Producer;
import common.DVD;
import common.DVDService;

@Controller
public class AdminController {
	
	private String QUEUE = "queue";
	private String MESS = "";
	
	
	@RequestMapping(value = "/newDVD", method = RequestMethod.GET)
	public ModelAndView newDVD(ModelAndView model) {
		DVD newDVD = new DVD();	
		model.addObject("DVD", newDVD);
		model.setViewName("formDVD");
		return model;
	}
	
	@RequestMapping(value = "/saveDVD", method = RequestMethod.POST)
	public ModelAndView saveDVD(ModelAndView model,@Valid @ModelAttribute("DVD") DVD DVD, BindingResult result) throws IOException, TimeoutException {
		if(result.hasErrors()) {
			model.setViewName("formDVD");
			return model;
		}
		
		QueueConsumer consumer = new QueueConsumer(QUEUE);
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		
		Producer producer = new Producer(QUEUE);
		//consumer.getDVD(DVD.toString());
		
	
	
	//producer.sendEmail(DVD);
	
		HashMap message = new HashMap<DVD,String>();
		
		message.put(QUEUE,DVD);	
		
		if(	consumer.getDVD(message.get(QUEUE).toString())){
			MESS = "DVD Added";
		}
		else{
			MESS = "Error while loading the DVD";
		}
		
		
		producer.sendMessage(message);
		
		
		
		model.setViewName("redirect:/listDVD");
		return model;
}

	
	@RequestMapping("/listDVD")
	public ModelAndView list(ModelAndView model){
		model.addObject("message",MESS);
		model.setViewName("listDVD");
		
		return model;
	}
}