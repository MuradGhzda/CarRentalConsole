package az.rent.main.dao.inter;

public interface UserInterface {
    int getId();
    String getName();
    void setName(String name);
    String getPassword();
    boolean isAdmin();
    void setAdmin(boolean isAdmin);
}
