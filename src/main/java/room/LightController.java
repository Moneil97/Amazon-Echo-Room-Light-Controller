package room;

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

	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {}
	
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
		return sayWithCard("You have summoned Light Controller");
	}
	
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
		
		
		Intent intent = request.getIntent();
		String name = intent.getName();
		
		if (name.equals("ChangeBrightness")) {
			return say("Changing brightness");
		}
		else if (name.equals("ChangeColor")) {
			return say("Changing color");
		}
		else if (name.equals("ChangeStatus")) {
			return say("Changing power");
		}
		else {
			return say("Invalid Intent: " + name);
		}
		
		
		
		
		//return sayWithCard("You have sent an intent to Light Controller");
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
