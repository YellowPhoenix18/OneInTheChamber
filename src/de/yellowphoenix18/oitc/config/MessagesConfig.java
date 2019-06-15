package de.yellowphoenix18.oitc.config;

import java.io.File;

import de.yellowphoenix18.oitc.utils.Message;

public class MessagesConfig extends Config {

	public MessagesConfig(File file) {
		super(file);
		
		for(Message message : Message.values()) {
			message.setText(
				translateColor(
					(String) this.setDefault(message.getConfigName(), message.getText())
				)
			);
		}
	}
	
	public String getMessage(Message message) {
		String path = message.getConfigName();
		if(this.contains(path)) {
			return (String) this.get(path);
		}
		return null;
	}
	
	public void setMessage(Message message, String text) {
		message.setText(translateColor(text));
		this.set(message.getConfigName(), text);
	}
	
	private String translateColor(String text) {
		return text.replace("&", "§");
	}

}
