package com.example.dictionary.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private WordAdapter wordAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        wordAdapter = new WordAdapter();
        RecyclerView recyclerView = binding.recyclerWords;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(wordAdapter);

        final TextView textEmpty = binding.textEmpty;

        homeViewModel.getWords().observe(getViewLifecycleOwner(), words -> {
            wordAdapter.setWordList(words);
            textEmpty.setVisibility(words == null || words.isEmpty() ? View.VISIBLE : View.GONE);
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterWords(homeViewModel, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterWords(homeViewModel, newText);
                return true;
            }
        });

        return root;
    }

    private void filterWords(HomeViewModel viewModel, String query) {
        List<WordItem> allWords = viewModel.getWords().getValue();
        if (allWords == null) return;

        if (query == null || query.trim().isEmpty()) {
            wordAdapter.setWordList(allWords);
            binding.textEmpty.setVisibility(allWords.isEmpty() ? View.VISIBLE : View.GONE);
            return;
        }

        List<WordItem> filtered = new ArrayList<>();
        String lowerQuery = query.toLowerCase();
        for (WordItem item : allWords) {
            if (item.getChineseWord().contains(query)
                    || item.getJapaneseTranslation().toLowerCase().contains(lowerQuery)) {
                filtered.add(item);
            }
        }
        wordAdapter.setWordList(filtered);
        binding.textEmpty.setVisibility(filtered.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}