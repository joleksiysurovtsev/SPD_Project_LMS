package com.lms.spd.repository.parsers;

import java.io.*;
import java.nio.CharBuffer;
import java.util.stream.Stream;

public class ReaderWrapper extends BufferedReader implements AutoCloseable {

    public ReaderWrapper(Reader in, int sz) {
        super(in, sz);
    }

    public ReaderWrapper(Reader in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        return super.read();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return super.read(cbuf, off, len);
    }

    @Override
    public String readLine() throws IOException {
        return super.readLine();
    }

    @Override
    public long skip(long n) throws IOException {
        return super.skip(n);
    }

    @Override
    public boolean ready() throws IOException {
        return super.ready();
    }

    @Override
    public boolean markSupported() {
        return super.markSupported();
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        super.mark(readAheadLimit);
    }

    @Override
    public void reset() throws IOException {
        super.reset();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public Stream<String> lines() {
        return super.lines();
    }

    @Override
    public int read(CharBuffer target) throws IOException {
        return super.read(target);
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        return super.read(cbuf);
    }

    @Override
    public long transferTo(Writer out) throws IOException {
        return super.transferTo(out);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
