package com.example.dictionary.ui.home;

public class WordItem {
    private final String chineseWord;
    private final String japaneseTranslation;

    public WordItem(String chineseWord, String japaneseTranslation) {
        this.chineseWord = chineseWord;
        this.japaneseTranslation = japaneseTranslation;
    }

    public String getChineseWord() {
        return chineseWord;
    }

    public String getJapaneseTranslation() {
        return japaneseTranslation;
    }
}
