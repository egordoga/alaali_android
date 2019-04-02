package ua.alaali.model;

import java.util.Collection;
import java.util.List;

public class Visitor {
    public long id;
    public String name;
    public String pass;
    public String email;
    public String phone;
    public String activationCode;
    public byte active;
    public Rating rating;
    public List<Product> products;
    public Basket basket;
    public Collection<Role> roles;
}
