package vi.talii.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={"classpath:app-context.xml"})
public class InitAppContextBase {


    @Autowired
    protected PlayerService playerService;
    @Autowired
    protected GameManager gameManager;
    @Autowired
    protected DeckService deckService;




}
