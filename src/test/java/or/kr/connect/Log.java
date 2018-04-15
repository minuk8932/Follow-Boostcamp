package or.kr.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
	Logger log = LoggerFactory.getLogger(Log.class);

    public void someMethod() {
        log.info("Hello world");
    }
}
