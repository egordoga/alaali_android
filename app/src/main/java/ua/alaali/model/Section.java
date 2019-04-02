package ua.alaali.model;

import java.io.Serializable;

public class Section implements Serializable {
    public long id;
    public String name;

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
