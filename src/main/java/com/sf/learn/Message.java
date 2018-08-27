package com.sf.learn;

import lombok.Data;

import java.util.Date;

/**
 * @author lijie.zh
 */
@Data
public class Message {
    private Long id;

    private String msg;

    private Date sendTime;

}