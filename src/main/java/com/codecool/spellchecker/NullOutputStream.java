package com.codecool.spellchecker;

import java.io.OutputStream;

public class NullOutputStream
        extends OutputStream
{
    public NullOutputStream() {}

    public void close() {}

    public void flush() {}

    public void write(byte[] paramArrayOfByte) {}

    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {}

    public void write(int paramInt) {}
}
