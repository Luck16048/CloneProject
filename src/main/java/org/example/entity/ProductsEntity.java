package org.example.entity;

public class ProductsEntity {
    private int id;
    private String name;
    private int number;
    private StatusEnum status;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getNumber(){
        return number;
    }
    public void setNumber(int number){
        this.number = number;
    }

    public StatusEnum getStatus(){
        return status;
    }
    public void setStatus(StatusEnum status){
        this.status = status;
    }
}
