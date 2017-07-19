package room;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.Sdk;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class StreamHandler extends SpeechletRequestStreamHandler {

	private static final Set<String> supportedApplicationIds;

    static {
    	
    	System.setProperty(Sdk.DISABLE_REQUEST_SIGNATURE_CHECK_SYSTEM_PROPERTY, "true");
		System.setProperty(Sdk.SUPPORTED_APPLICATION_IDS_SYSTEM_PROPERTY, "");
		System.setProperty(Sdk.TIMESTAMP_TOLERANCE_SYSTEM_PROPERTY, ""); 
		
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
        // */
        supportedApplicationIds = new HashSet<String>();
         //supportedApplicationIds.add("amzn1.echo-sdk-ams.app.[amzn1.ask.skill.0b743978-3883-43ae-8353-97622a6f6cbe]");
        supportedApplicationIds.add("amzn1.ask.skill.0b743978-3883-43ae-8353-97622a6f6cbe");
    }
	
	static {
		
	}



    public StreamHandler() {
        super(new LightController(), supportedApplicationIds);
    }

}
