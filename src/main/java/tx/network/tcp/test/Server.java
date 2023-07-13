package tx.network.tcp.test;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.Promise;
import io.vertx.core.net.NetServer;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Server extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", Server.class.getName());
  }

  @Override
  public void start(Promise<Void> startPromise) {
    NetServer server = vertx.createNetServer().connectHandler(socket -> {
      System.out.println("Client connected");

      // Read the client message
      socket.handler(buffer -> {
        System.out.println("Client message: " + buffer.toString());

        // Send a response to the client
        socket.write("Hello, client!");
      });
    }).listen(1234, ar -> {
      if (ar.succeeded()) {
        System.out.println("TCP server started on port 8080");
      } else {
        System.out.println("Error starting TCP server: " + ar.cause());
        startPromise.fail(ar.cause());
      }
    });

//    server.connectHandler(socket -> {
//      System.out.println("Client connected");
//
//      // Read the client message
//      socket.handler(buffer -> {
//        System.out.println("Client message: " + buffer.toString());
//
//        // Send a response to the client
//        socket.write("Hello, client!");
//      });
//    });
  }

//  @Override
//  public void start() throws Exception {
//
//    vertx.createNetServer().connectHandler(sock -> {
//
//      // Create a pump
//      Pump.pump(sock, sock).start();
//
//    }).listen(1234);
//
//    System.out.println("Echo server is now listening");
//
//  }
}
