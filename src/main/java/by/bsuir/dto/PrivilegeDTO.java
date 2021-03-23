package by.bsuir.dto;

public class PrivilegeDTO {
    private int idPrivilege;
    private String name;
    private float effect;

    public PrivilegeDTO() {
    }

    public PrivilegeDTO(int idPrivilege, String name, float effect) {
        this.idPrivilege = idPrivilege;
        this.name = name;
        this.effect = effect;
    }

    public int getIdPrivilege() {
        return idPrivilege;
    }

    public void setIdPrivilege(int idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getEffect() {
        return effect;
    }

    public void setEffect(float effect) {
        this.effect = effect;
    }


}
