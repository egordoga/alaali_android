package ua.alaali.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {
    public long id;
    public String name;
    public int quantity;         //понятно, что лучше Double. а еще лучше BigDecimal, но пока для упрощения так
    public int cost;
    public int quantityForOder;
    public String description;
    public byte[] pictureFirst;
    public byte[] picture2;
    public byte[] picture3;
    public byte[] picture4;
    public byte[] picture5;
    public Section section;
    //public Visitor visitor;
//    public List<Basket> baskets;
  //  public MultipartFile[] files;
}
