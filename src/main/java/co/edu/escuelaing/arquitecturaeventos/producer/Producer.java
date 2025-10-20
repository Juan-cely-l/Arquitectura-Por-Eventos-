package co.edu.escuelaing.arquitecturaeventos.producer;

import co.edu.escuelaing.arquitecturaeventos.connection.RedisTemplateService;
import co.edu.escuelaing.arquitecturaeventos.receiver.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class Producer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String CHANNEL = "chat";

    private final RedisTemplateService redisTemplate;
    private final RedisMessageListenerContainer listenerContainer;
    private final Receiver receiver;

    @Inject
    public Producer(RedisTemplateService redisTemplate,
                   RedisMessageListenerContainer listenerContainer,
                   Receiver receiver) {
        this.redisTemplate = redisTemplate;
        this.listenerContainer = listenerContainer;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Iniciando Producer - Publicador de mensajes Redis");
        logger.info("Canal de comunicacion: {}", CHANNEL);

        // Publicar mensajes de ejemplo
        publishMessage("Hola desde Spring Boot!");
        publishMessage("Arquitectura basada en eventos con Redis");
        publishMessage("Mensaje de prueba #3");

        // Esperar un momento para que se procesen los mensajes
        Thread.sleep(2000);

        logger.info("Total de mensajes recibidos: {}", receiver.getMessageCount());
        logger.info("Producer finalizado");
    }

    /**
     * Publica un mensaje en el canal Redis
     * @param message el mensaje a publicar
     */
    public void publishMessage(String message) {
        logger.info("Publicando mensaje: {}", message);
        redisTemplate.convertAndSend(CHANNEL, message);
    }

    /**
     * Publica multiples mensajes en el canal Redis
     * @param messages los mensajes a publicar
     */
    public void publishMessages(String... messages) {
        for (String message : messages) {
            publishMessage(message);
        }
    }
}

