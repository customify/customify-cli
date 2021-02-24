package com.customify.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class CustomizedObjectOutputStream extends ObjectOutputStream {
    protected CustomizedObjectOutputStream() throws IOException, SecurityException {
        super();
        // TODO Auto-generated constructor stub
    }
    public CustomizedObjectOutputStream(OutputStream out) throws IOException {
        /**Rewrite calling ObjectOutputStream stream*/
        super(out);
    }
    /**
     * Rewrite the method of writing file header
     * If it is the first input, write the file header, if it is not the first input, do not write the file header
     */
    @Override
    protected void writeStreamHeader() throws IOException {
        // TODO Auto-generated method stub
        return;
    }
}
