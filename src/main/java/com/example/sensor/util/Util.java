package com.example.sensor.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

  public static LocalDateTime dateFormatter(String date) {
    String newDate = date.replaceAll("[()\\[\\]]", "");
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    LocalDateTime dateTime = LocalDateTime.parse(newDate, formatter);

    return dateTime;
  }
}
