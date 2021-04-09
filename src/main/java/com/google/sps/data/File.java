package com.google.sps.data;

/** A file uploaded */
public final class File {

  private final long id;
  private final String fileName;
  private final long timestamp;

  public File(long id, String fileName, long timestamp) {
    this.id = id;
    this.fileName = fileName;
    this.timestamp = timestamp;
  }
}