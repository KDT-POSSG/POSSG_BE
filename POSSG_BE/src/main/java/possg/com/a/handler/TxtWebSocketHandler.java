package possg.com.a.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import possg.com.a.util.NaverCloudUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
public class TxtWebSocketHandler extends TextWebSocketHandler {
	
	@Autowired
	NaverCloudUtil naverUtil;
	
	private ByteBuffer audioBuffer;
	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private ScheduledFuture<?> silenceTimeout;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		System.out.println("Txt 1");
	  audioBuffer = ByteBuffer.allocate(1024 * 1024);
	}
	
	@Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);
        //session.sendMessage(new TextMessage("Hello, Client!"));
    }
	
	@Override
	public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
	  System.out.println("Txt 2");
	  ByteBuffer payload = message.getPayload();
	  audioBuffer.put(payload);
	  
	  // Cancel any existing silence timeout
      if (silenceTimeout != null) {
          silenceTimeout.cancel(true);
      }

      // Start a new silence timeout
      silenceTimeout = scheduler.schedule(() -> {
          try {
              session.close(CloseStatus.NORMAL);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }, 5, TimeUnit.SECONDS);
	  
	  // Submit to thread for STT processing and get the result
	  executor.submit(() -> {
		  System.out.println("Txt submit");
		  try {
			Path dir = Paths.get("D:\\finalProject\\POSSG_BE\\POSSG_BE\\src\\main\\webapp\\upload");
	        // Save the audio buffer to a temporary file
			Path tempFile = Files.createTempFile(dir,"audio", ".wav");
	        //Path tempFile = Files.createTempFile("audio", ".wav");
	        System.out.println("path " + tempFile.toString());
	        Files.write(tempFile, audioBuffer.array(), StandardOpenOption.WRITE);

	        // Call the STT process
	        String result = naverUtil.processSTT(tempFile.toString());
	        
	        // Clear the audio buffer
	        audioBuffer.clear();
	        
	        // Send the STT result back to the client
	        session.sendMessage(new TextMessage(result));
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	  });
	}
}
