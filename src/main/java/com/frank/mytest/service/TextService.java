package com.frank.mytest.service;

import com.frank.mytest.entity.Text;
import com.frank.mytest.respository.TextRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TextService {

    private final TextRepository textRepository;

    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public void insertText(String text) {
        Text newText = Text.builder().text(text).build();
        textRepository.save(newText);
    }

    public Optional<Text> findText(Integer id) {
        return textRepository.findById(id);
    }
}
