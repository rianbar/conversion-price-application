package com.rian.bookservice.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book")
public class BookModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author", nullable = false, length = 180)
    private String author;
    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date launchDate;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false, length = 250)
    private String title;
    @Transient
    private String currency;
    @Transient
    private String environment;

    public BookModel() {
    }

    public BookModel(Long id, String author, String title, Date launchDate, Double price, String currency, String environment) {
        this.id = id;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
        this.currency = currency;
        this.environment = environment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
