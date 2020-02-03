package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "test", false));

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "test name", trelloListDtos));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test name", fetchedTrelloBoards.get(0).getName());
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        Trello trello = new Trello(1, 1);
        AttachmentsByType attachmentsByType = new AttachmentsByType(trello);
        Badges badges = new Badges(1, attachmentsByType);
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "test name", "test.com", badges);
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test description", "test pos", "1");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //when
        CreatedTrelloCardDto createdTrelloCardDtos = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals("1", createdTrelloCardDtos.getId());
        assertEquals("test name", createdTrelloCardDtos.getName());
        assertEquals("test.com", createdTrelloCardDtos.getShortUrl());
        assertEquals(badges, createdTrelloCardDtos.getBadges());
        assertEquals(1, createdTrelloCardDtos.getBadges().getAttachmentsByType().getTrello().getCard());
        assertEquals(1, createdTrelloCardDtos.getBadges().getAttachmentsByType().getTrello().getBoard());
        assertEquals(1, createdTrelloCardDtos.getBadges().getVotes());
    }
}