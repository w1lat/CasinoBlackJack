package vi.talii.rest.listener;

import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by serhii on 26.10.15.
 */
public class SpringContextInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String appContextLocation = sce.getServletContext().getInitParameter("contextConfigLocation");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(appContextLocation);
        sce.getServletContext().setAttribute("springContext", appContextLocation);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
