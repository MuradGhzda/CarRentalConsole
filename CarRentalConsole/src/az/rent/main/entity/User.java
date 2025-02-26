package az.rent.main.entity;

import az.rent.main.dao.inter.UserInterface;

public class User implements UserInterface {
    private static int code = 1; // Initial user ID starts from 1
    private int id;
    private String name;
    private String password;
    private boolean isAdmin;

    public User(String name, String password, boolean isAdmin) {
        this.id = code++;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', isAdmin=%b}", id, name, isAdmin);
    }
}
