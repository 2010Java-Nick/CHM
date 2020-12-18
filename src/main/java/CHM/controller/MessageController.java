package CHM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import CHM.model.Message;
import CHM.service.MessageService;


@RestController
public class MessageController {

	MessageService messageService;
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@RequestMapping(path = "/message", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> createMessage(@RequestBody Message message) {
		
		Integer newMessageId;
		ResponseEntity<Integer> re = new ResponseEntity<Integer>(new Integer(-1), HttpStatus.BAD_REQUEST);
			newMessageId = new Integer(messageService.createMessage(message));
			if (newMessageId != -1) {
				re = new ResponseEntity<Integer>(newMessageId, HttpStatus.CREATED);
			}
		return re;
	}
	
	@RequestMapping(path = "/message/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Message> readMessageById(@PathVariable(name = "id")int messageId) {
		
		Message message = messageService.readMessageById(messageId);
		ResponseEntity<Message> re = new ResponseEntity<Message>(message, message == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@RequestMapping(path = "/message", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Message>> readAllMessages() {
		
		List<Message> messageList = messageService.readAllMessages();
		ResponseEntity<List<Message>> re = new ResponseEntity<List<Message>>(messageList, messageList == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return re;
	}
	
	@RequestMapping(path = "/message", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<Message> updateMessage(@RequestBody Message message) {
		
		Message updatedMessage = null;
		ResponseEntity<Message> re = new ResponseEntity<Message>(updatedMessage, HttpStatus.BAD_REQUEST);
		updatedMessage = messageService.updateMessage(message);
		if (updatedMessage != null) {
			re = new ResponseEntity<Message>(updatedMessage, HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(path = "/message", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Boolean> deleteMessage(@RequestBody Message message){
		
		Boolean deleted = messageService.deleteMessage(message);
		ResponseEntity<Boolean> re = new ResponseEntity<Boolean>(deleted, deleted ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
		return re;
	}
}
