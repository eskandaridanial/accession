package com.accession.io.message.models;

/**
* @author: Danial Eskandari
* @createdAt: 2024-11-11 13:46:04
*/
public record ResponseModel(

        Integer code,

        Integer status,

        String message,

        Boolean displayReason
) { }