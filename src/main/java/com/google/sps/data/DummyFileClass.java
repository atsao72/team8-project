package com.google.sps.data;

/** An file uploaded */
public final class File {

  private final long id;
  private final String fileName;
  private final long timestamp;

  public Task(long id, String fileName, long timestamp) {
    this.id = id;
    this.fileName = fileName;
    this.timestamp = timestamp;
  }
}