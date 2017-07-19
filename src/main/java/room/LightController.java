package room;

import java.util.ArrayList;
import java.util.Arrays;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class LightController implements Speechlet{
	
	ArrayList<String> availableColors = new ArrayList<String>(Arrays.asList(new String[] {"red", "green", "blue", "white", "black"}));

	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
		
	}
	
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
		return sayWithCard("You have summoned Light Controller");
	}
	
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
		
		
		Intent intent = request.getIntent();
		String name = intent.getName();
		
		if (name.equals("ChangeBrightness")) {
			
			if (intent.getSlots().isEmpty())
				return sayWithCard("No brightness value given");
			
			try {
				int brightness = Integer.parseInt(intent.getSlot("brightness").getValue());
				return sayWithCard("Brightness set to: " + brightness + "%");
			}
			catch(Exception e) {
				return sayWithCard("Failed to change brightness, received value: " + intent.getSlot("brightness").getValue());
			}
		}
		else if (name.equals("ChangeColor")) {
			
			if (intent.getSlots().isEmpty())
				return sayWithCard("No color given");
			
			String color = intent.getSlot("color").getValue();
			
			if (availableColors.contains(color))
				return sayWithCard("Color set to: " + color);
			else
				return sayWithCard(color + " is not an available color");
			
			
		}
		else if (name.equals("ChangeStatus")) {
			
			if (intent.getSlots().isEmpty())
				return sayWithCard("No power status given");
			
			String power = intent.getSlot("status").getValue();
			
			return sayWithCard("Lights turned: " + power);
		}
		else {
			return sayWithCard("Invalid Intent: " + name);
		}
		
	}

	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {}
	
	protected SpeechletResponse say(String text) {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(text);

        return SpeechletResponse.newTellResponse(outputSpeech);
    }
	
	protected SpeechletResponse sayWithCard(String text) {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(text);
        
        Card card = new SimpleCard();
        card.setTitle(text);
        
        return SpeechletResponse.newTellResponse(outputSpeech, card);
    }
	
	protected SpeechletResponse askForResponse(String stringOutput, String repromptText) {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(stringOutput);

        PlainTextOutputSpeech repromptOutputSpeech = new PlainTextOutputSpeech();
        repromptOutputSpeech.setText(repromptText);
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);

        return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
    }

}
