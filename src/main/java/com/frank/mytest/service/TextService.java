package com.frank.mytest.service;

import com.frank.mytest.entity.Text;
import com.frank.mytest.respository.TextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    // test method for jpa delete and insert in same transaction
    @Transactional(rollbackFor = Exception.class)
    public void deleteAndInsertText(Integer id, String text) {
        Optional<Text> optional = textRepository.findById(id);
        textRepository.deleteAllInBatch(List.of(optional.get()));
        Text newText = Text.builder().text(text).build();
        textRepository.save(newText);
    }
}
