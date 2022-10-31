package pages;

import java.util.List;



import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Settings {
	
	
	
	@AndroidFindBy(id = "")
	private AndroidElement recordAudioTitle;
	
	
	
	
	public void getTitle() {
		//boolean alertSize =recordAudioTitle.size()>0;
		String title=recordAudioTitle.getText();
		
	}

}
