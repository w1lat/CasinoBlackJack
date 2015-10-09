package vi.talii;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vi.talii.view.ConsoleView;

/**
 * Created by Vitalii on 06.10.2015.
 */
public class RunApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:app-context.xml");
        ConsoleView view = applicationContext.getBean(ConsoleView.class);
        view.start();
    }
}
