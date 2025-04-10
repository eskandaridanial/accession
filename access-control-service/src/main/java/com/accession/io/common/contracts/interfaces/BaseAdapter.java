package com.accession.io.common.contracts.interfaces;

import com.accession.io.common.contracts.records.BaseResponse;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 12:17:57
 */
public interface BaseAdapter<Input, Output> {

    BaseResponse<Output> execute(Input input);
}
