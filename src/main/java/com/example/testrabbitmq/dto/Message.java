package com.example.testrabbitmq.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Message implements Serializable {
   private String title;
   private String content;
}
