package model.accesscontrol;

import java.util.ArrayList;

/**
 * Daitqhe182481
 *
 * @author Zeldais
 */
public class Role {

    private int id;
    private String name;
    private ArrayList<User> users = new ArrayList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }
    private ArrayList<Feature> features = new ArrayList<>();
    
}
