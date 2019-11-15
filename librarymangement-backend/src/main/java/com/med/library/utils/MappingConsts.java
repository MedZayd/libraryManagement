package com.med.library.utils;

public final class MappingConsts {

    public static final String BOOKS = "/books";
    public static final String BOOK = "/{bookId}";
    public static final String BOOK_AUTHOR = "/{bookId}/author/{authorId}";
    public static final String BOOK_PUBLISHER = "/{bookId}/publisher/{publisherId}";

    /**
     The caller should be prevented from constructing objects of
     this class, by declaring this private constructor.
     */
    private MappingConsts() {
        throw new AssertionError();
    }
}
