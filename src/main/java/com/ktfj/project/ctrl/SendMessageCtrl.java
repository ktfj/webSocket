package com.ktfj.project.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SendMessageCtrl")
public class SendMessageCtrl {
	  @Autowired
	  private WebSocket webSocket;
	  
      @GetMapping("sendMessage") 
	  public void sendMessage(@RequestParam String msg){
    	  webSocket.sendMessage(msg);
	  }
}
