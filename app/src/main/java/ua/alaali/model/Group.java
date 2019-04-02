package ua.alaali.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Group implements Serializable {

    public long id;
    public String name;
    public List<Section> sections;


    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
