package com.ktfj.project.ctrl;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;
@Component
@ServerEndpoint(value="/WebSocket/{userId}")
public class WebSocket {
	
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocket> webSocketSet=new CopyOnWriteArraySet<>();
    
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private static Session session;
    
	@OnOpen
	public void open(@PathParam(value = "userId") String userId,Session session){
		 this.session=session;
		  System.out.println("用户ID:"+userId);
		 webSocketSet.add(this);
	}
	/**
	 * 连接关闭时，触发。
	 * @param session
	 * @throws IOException
	 */
	@OnClose
	public void onclose() throws IOException{
		webSocketSet.remove(this);;
	}
	/**
	 * 接收客户端的消息。
	 * @param session
	 */
	@OnMessage
	public void onMessage(String message,Session session){
		System.out.println("接收客户端的消息："+message);
		//群发消息。
//		for(WebSocket webSocket:webSocketSet){
//			  webSocket.sendMessage("服务端群发消息");
//		}	
	}
    /**
     * 使用session发送消息。
     * @param msg
     */
	public void sendMessage(String msg){
		try {
			this.session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
