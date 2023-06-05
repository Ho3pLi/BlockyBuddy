package ho3pli.mcapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class ApiMain {

    private static final Logger LOGGER = LogUtils.getLogger();
    public static void main() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/playerPosition", new PlayerPositionHandler());
        server.createContext("/api/targetedBlock", new TargetedBlockHandler());
        server.createContext("/api/targetedFluid", new TargetedFluidHandler());
        server.createContext("/api/targetedEntity", new TargetedEntityHandler());
        server.createContext("/api/health", new HealthHandler());
        server.start();
        LOGGER.info("API SERVER STARTED AT http://localhost:8080/");
    }

    static class PlayerPositionHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String playerPosition = String.valueOf(PlayerInfo.getPosition());
            exchange.sendResponseHeaders(200, playerPosition.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(playerPosition.getBytes());
            outputStream.close();
        }
    }

    static class TargetedBlockHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String targetedBlock = PlayerInfo.getTargetedBlock();
            exchange.sendResponseHeaders(200, targetedBlock.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(targetedBlock.getBytes());
            outputStream.close();
        }
    }

    static class TargetedFluidHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String targetedFluid = PlayerInfo.getTargetedFluid();
            exchange.sendResponseHeaders(200, targetedFluid.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(targetedFluid.getBytes());
            outputStream.close();
        }
    }

    static class TargetedEntityHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String targetedEntity = PlayerInfo.getTargetedEntity();
            exchange.sendResponseHeaders(200, targetedEntity.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(targetedEntity.getBytes());
            outputStream.close();
        }
    }

    static class HealthHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String health = String.valueOf(PlayerInfo.getHealth());
            exchange.sendResponseHeaders(200, health.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(health.getBytes());
            outputStream.close();
        }
    }
}

