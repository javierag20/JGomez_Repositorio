/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serviciorest_jg;

import java.io.Serializable;

/**
 *
 * @author Javier Gomez
 */
public class Post implements Serializable{
    private Integer id;
    private Integer userid;
    private String title;
    private String body;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body.substring(0, 20);
    }

    public void setBody(String body) {
        this.body = body.substring(0, 20);
    }
    
}
