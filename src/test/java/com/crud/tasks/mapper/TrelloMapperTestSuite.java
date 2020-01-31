package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void shouldReturnBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "Test", new ArrayList<>()));
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //When
        String id = trelloBoards.get(0).getId();
        String name = trelloBoards.get(0).getName();
        List<TrelloList> trelloBoardList = trelloBoards.get(0).getLists();

        //Then
        assertEquals("1", id);
        assertEquals("Test", name);
        assertEquals(0, trelloBoardList.size());
    }

    @Test
    public void shouldReturnBoardDtos() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "Test", new ArrayList<>()));
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //When
        String id = trelloBoardDtos.get(0).getId();
        String name = trelloBoardDtos.get(0).getName();
        List<TrelloListDto> trelloListDtos = trelloBoardDtos.get(0).getLists();

        //Then
        assertEquals("1", id);
        assertEquals("Test", name);
        assertEquals(0, trelloListDtos.size());
    }

    @Test
    public void shouldReturnTrelloList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "Test", false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //When
        String id = trelloLists.get(0).getId();
        String name = trelloLists.get(0).getName();
        boolean isClosed = trelloLists.get(0).isClosed();

        //Then
        assertEquals("1", id);
        assertEquals("Test", name);
        assertFalse(isClosed);
    }

    @Test
    public void shouldReturnTrelloListDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "Test", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //When
        String id = trelloListDtos.get(0).getId();
        String name = trelloListDtos.get(0).getName();
        boolean isClosed = trelloListDtos.get(0).isClosed();

        //Then
        assertEquals("1", id);
        assertEquals("Test", name);
        assertFalse(isClosed);
    }

    @Test
    public void shouldReturnTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test card", "Testing description",
                "Test pos", "Test list id");
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //When
        String name = trelloCard.getName();
        String description = trelloCard.getDescription();
        String pos = trelloCard.getPos();
        String listId = trelloCard.getListId();

        //Then
        assertEquals("Test card", name);
        assertEquals("Testing description", description);
        assertEquals("Test pos", pos);
        assertEquals("Test list id", listId);
    }

    @Test
    public void shouldReturnTrelloCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Test card", "Testing description",
                "Test pos", "Test list id");
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //When
        String name = trelloCardDto.getName();
        String description = trelloCardDto.getDescription();
        String pos = trelloCardDto.getPos();
        String listId = trelloCardDto.getListId();

        //Then
        assertEquals("Test card", name);
        assertEquals("Testing description", description);
        assertEquals("Test pos", pos);
        assertEquals("Test list id", listId);
    }
}