package com.accession.io.common.contracts.interfaces;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-15 21:01:26
 */
public interface BaseEventHandler<Event> {

    void handle(Event event);
}
