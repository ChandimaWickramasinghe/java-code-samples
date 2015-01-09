package com.mysamples.springsamples.codewithinterface;

public class TextEditor {

	private SpellChecker spellChecker;

	public SpellChecker getSpellChecker() {
		System.out.println("getSpellChecker");
		return spellChecker;
	}

	public void setSpellChecker(SpellChecker spellChecker) {
		System.out.println("setSpellChecker");
		this.spellChecker = spellChecker;
	}

	public void setText() {
		System.out.println("setText");
		spellChecker.checkSpellings();
	}
}
