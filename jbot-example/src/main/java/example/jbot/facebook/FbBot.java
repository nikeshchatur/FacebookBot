package example.jbot.facebook;

import me.nikesh.jbot.core.common.Controller;
import me.nikesh.jbot.core.common.EventType;
import me.nikesh.jbot.core.common.JBot;
import me.nikesh.jbot.core.facebook.Bot;
import me.nikesh.jbot.core.facebook.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * A simple Facebook Bot. You can create multiple bots by just
 * extending {@link Bot} class like this one. Though it is
 * recommended to create only bot per jbot instance.
 * 
 * @author nikesh
 * @version 17/09/2016
 */



@JBot
@Profile("facebook")
public class FbBot extends Bot {

	private static final Logger logger = LoggerFactory.getLogger(FbBot.class);
    /**
     * Set this property in {@code application.properties}.
     */
    @Value("${fbBotToken}")
    private String fbToken;

    /**
     * Set this property in {@code application.properties}.
     */
    @Value("${fbPageAccessToken}")
    private String pageAccessToken;

    @Override
    public String getFbToken() {
        return fbToken;
    }

    @Override
    public String getPageAccessToken() {
        return pageAccessToken;
    }

    /**
     * Sets the "Get Started" button with a payload "hi". It also set the "Greeting Text" which the user sees when it
     * opens up the chat window. Uncomment the {@code @PostConstruct} annotation only after you have verified your 
     * webhook.
     */
    @PostConstruct
    public void init() {
        setGetStartedButton("hi");
        setGreetingText(new Payload[]{new Payload().setLocale("default").setText("JBot is a Java Framework to help" +
                " developers make Facebook bots easily. You can see a quick demo by clicking " +
                "the \"Get Started\" button.")});
    }

    /**
     * This method gets invoked when a user clicks on the "Get Started" button or just when someone simply types
     * hi, hello or hey. When it is the former, the event type is {@code EventType.POSTBACK} with the payload "hi"
     * and when latter, the event type is {@code EventType.MESSAGE}.
     *
     * @param event
     */
    @Controller(events = {EventType.MESSAGE})
    public void onGetStarted(Event event) {
    	
        // quick reply buttons
        Button[] quickReplies = new Button[]{
                new Button().setContentType("text").setTitle("Sure").setPayload("yes"),
                new Button().setContentType("text").setTitle("Nope").setPayload("no")
        };
        logger.debug("event message---->"+ event.getMessage(), event);
        reply(event, new Message().setText("Hello, I am JBot. Would you like to see more for ?"+event.getMessage().getText()).setQuickReplies(quickReplies));
    }

 
}
