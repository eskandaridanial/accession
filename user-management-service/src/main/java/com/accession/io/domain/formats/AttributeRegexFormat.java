package com.accession.io.domain.formats;

import java.util.regex.Pattern;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 12:40:18
 */
public abstract class AttributeRegexFormat {

    public static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z0-9\\-_ ]{5,32}$");

    public static final Pattern ENDPOINT_PATTERN = Pattern.compile("^/(?:([^/]+)/)*([^/]+)(?:/([^/]+))*(?:/([^/]+))?$");

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,32}$");

    public static final Pattern HTTP_METHOD_PATTERN = Pattern.compile("GET|POST|PUT|PATCH");
}
