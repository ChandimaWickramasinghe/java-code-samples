package com.mysamples.springsamples.annotation.two;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public TextEditor textEditor() {
		TextEditor editor = new TextEditor();
		editor.setSpellChecker(spellChecker());
		return editor;

	}

	@Bean(initMethod = "init")
	public SpellChecker spellChecker() {
		return new EnglishSpellChecker();
	}

}
