package vi.talii.rest.endpoint;

import org.springframework.context.ApplicationContext;
import vi.talii.service.GameManager;
import vi.talii.service.PlayerService;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;


public class InitServicesBase {


    @Context
    private ServletContext servletContext;

    // get gameManager and playerService beans from spring context to use them in endpoints
    @PostConstruct
    public void init() {
        ApplicationContext applicationContext = (ApplicationContext) servletContext
                .getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
        gameManager = applicationContext.getBean(GameManager.class);
        playerService = applicationContext.getBean(PlayerService.class);
    }

    protected GameManager gameManager;
    protected PlayerService playerService;
}
