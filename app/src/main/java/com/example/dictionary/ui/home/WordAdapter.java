package com.example.dictionary.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<WordItem> wordList = new ArrayList<>();

    public void setWordList(List<WordItem> wordList) {
        this.wordList = wordList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_word, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        WordItem item = wordList.get(position);
        holder.textChinese.setText(item.getChineseWord());
        holder.textJapanese.setText(item.getJapaneseTranslation());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView textChinese;
        TextView textJapanese;

        WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textChinese = itemView.findViewById(R.id.text_chinese);
            textJapanese = itemView.findViewById(R.id.text_japanese);
        }
    }
}
