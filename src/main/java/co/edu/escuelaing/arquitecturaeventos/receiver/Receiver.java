package co.edu.escuelaing.arquitecturaeventos.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private final AtomicInteger messageCounter = new AtomicInteger(0);

    /**
     * Metodo que recibe y procesa mensajes desde Redis
     * @param message el mensaje recibido
     */
    public void receiveMessage(String message) {
        int count = messageCounter.incrementAndGet();
        logger.info("Mensaje recibido #{}: {}", count, message);
    }

    /**
     * Obtiene el contador total de mensajes recibidos
     * @return el numero de mensajes recibidos
     */
    public int getMessageCount() {
        return messageCounter.get();
    }

    /**
     * Reinicia el contador de mensajes
     */
    public void resetCounter() {
        messageCounter.set(0);
        logger.info("Contador de mensajes reiniciado");
    }
}

