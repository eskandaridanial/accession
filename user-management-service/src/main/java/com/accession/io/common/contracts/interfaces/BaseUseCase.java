package com.accession.io.common.contracts.interfaces;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 12:17:57
 */
public interface BaseUseCase<Input, Output> {

    Output execute(Input input);
}
