package com.elon.feign.server.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = -5699625542142109964L;

    private int id = -1;

    private String name = "";

    private int age = 0;
}
