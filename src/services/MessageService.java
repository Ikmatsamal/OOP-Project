package services;

import models.system.Message;
import utils.Logger;

import patterns.singleton.DatabaseSingleton;
import models.user.User;

import java.util.List;

public class MessageService {
	
	

    private final DatabaseSingleton db =
            DatabaseSingleton.getInstance();

    public void sendMessage(
            User sender,
            String receiverEmail,
            String text
    ) {
        Message message = new Message(
                sender.getEmail(),
                receiverEmail,
                text
        );

        db.addMessage(message);
        Logger.info(
        	    sender.getEmail() +
        	    " sent message to " +
        	    receiverEmail
        	);
    }

    public List<Message> getInbox(User user) {
        return db.getMessages()
                .stream()
                .filter(message ->
                        message.getReceiverEmail()
                                .equalsIgnoreCase(user.getEmail()))
                .toList();
    }

    public List<Message> getSentMessages(User user) {
        return db.getMessages()
                .stream()
                .filter(message ->
                        message.getSenderEmail()
                                .equalsIgnoreCase(user.getEmail()))
                .toList();
    }
}