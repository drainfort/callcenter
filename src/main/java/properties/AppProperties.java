package properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Properties of the application
 * @author Jaime Romero
 *
 */
@Component
@PropertySource("classpath:app.properties")
@ConfigurationProperties
public class AppProperties {
	
	@Value("${queue-size}")
    private int queueSize;

	public int getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}
}
