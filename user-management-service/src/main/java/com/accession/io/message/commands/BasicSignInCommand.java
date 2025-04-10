package com.accession.io.message.commands;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:05:20
 */
public record BasicSignInCommand(

        String email,

        String password
) { }
