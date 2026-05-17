package interfaces;

import models.user.Employee;

public interface MessageSender {

    void sendMessage(
            Employee receiver,
            String text
    );
}