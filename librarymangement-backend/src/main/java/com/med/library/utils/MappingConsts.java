package com.med.library.utils;

public final class MappingConsts {

    public static final String BOOKS = "/books";
    public static final String BOOK = "/{bookId}";
    public static final String BOOK_SET = "/{bookId}/setAuthorsAndPublisher";
    public static final String BOOK_DETACH = "/{bookId}/detachAuthorsAndPublisher";

    public static final String AUTHORS = "/authors";
    public static final String AUTHOR = "/{authorId}";

    public static final String PUBLISHERS = "/publishers";
    public static final String PUBLISHER = "/{publisherId}";

    /**
     The caller should be prevented from constructing objects of
     this class, by declaring this private constructor.
     */
    private MappingConsts() {
        throw new AssertionError();
    }
}
