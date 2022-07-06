package com.example.soap.model;

public class ByteArray {
    private String bytes;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ByteArray(String bytes, String fileName) {
        this.bytes = bytes;
        this.fileName = fileName;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public ByteArray() {
    }
}
