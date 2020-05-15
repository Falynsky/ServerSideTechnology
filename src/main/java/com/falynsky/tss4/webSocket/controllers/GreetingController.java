package com.falynsky.tss4.webSocket.controllers;

import com.falynsky.tss4.webSocket.models.Greeting;
import com.falynsky.tss4.webSocket.models.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {


    @MessageMapping("/chat")
    @SendTo("/topic/chatRoom")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting(HtmlUtils.htmlEscape(message.getName()) +": " +  HtmlUtils.htmlEscape(message.getMsg()));
    }

}