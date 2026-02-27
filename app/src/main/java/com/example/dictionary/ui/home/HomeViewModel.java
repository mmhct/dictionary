package com.example.dictionary.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<WordItem>> mWords;

    public HomeViewModel() {
        mWords = new MutableLiveData<>();
        List<WordItem> sampleWords = new ArrayList<>();
        sampleWords.add(new WordItem("你好", "こんにちは"));
        sampleWords.add(new WordItem("谢谢", "ありがとう"));
        sampleWords.add(new WordItem("再见", "さようなら"));
        sampleWords.add(new WordItem("朋友", "友達（ともだち）"));
        sampleWords.add(new WordItem("学习", "勉強（べんきょう）"));
        mWords.setValue(sampleWords);
    }

    public LiveData<List<WordItem>> getWords() {
        return mWords;
    }

    public void addWord(WordItem word) {
        List<WordItem> current = mWords.getValue();
        List<WordItem> updated = new ArrayList<>();
        if (current != null) {
            updated.addAll(current);
        }
        updated.add(word);
        mWords.setValue(updated);
    }
}