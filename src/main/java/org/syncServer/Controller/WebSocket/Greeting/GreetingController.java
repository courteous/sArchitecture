package org.syncServer.Controller.WebSocket.Greeting;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        System.out.println("the controller has been hit");
    	Thread.sleep(1000); // simulated delay
        return new  Greeting("Hello, " + message.getName() + "!");
    }

}
