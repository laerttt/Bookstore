package com.example.bookstoreapplication.NoHeader;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class NoHeader extends ObjectOutputStream {

    public NoHeader(OutputStream out) throws IOException {
        super(out);
    }
    public void writeStreamHeader()
    {
        // don't do anything
    }
}