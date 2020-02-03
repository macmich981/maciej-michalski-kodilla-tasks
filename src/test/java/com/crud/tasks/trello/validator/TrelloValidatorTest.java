package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards() {
        //Given
        TrelloBoard trelloBoard = new TrelloBoard("0", "test", new ArrayList<>());
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "test name", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        trelloBoards.add(trelloBoard1);

        //When
        List<TrelloBoard> trelloBoardList = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(1, trelloBoardList.size());
        assertEquals("test name", trelloBoardList.get(0).getName());
    }
}