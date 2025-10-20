package co.edu.escuelaing.arquitecturaeventos.connection;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class RedisTemplateService extends StringRedisTemplate {

    @Inject
    public RedisTemplateService(RedisConnectionFactory connectionFactory) {
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }
}

