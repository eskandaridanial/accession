package com.accession.io.infrastructure.providers;

/**
* @author: Danial Eskandari
* @createdAt: 2024-11-11 13:26:32
*/
public interface QueueProvider {

    void publish(String exchange, String queue, Object message);
}
