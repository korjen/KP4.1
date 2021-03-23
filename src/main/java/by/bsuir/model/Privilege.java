package by.bsuir.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "privilege")
public class Privilege implements Serializable {
    private static final long serialVersionUID = 7L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrivilege;
    @Column
    private String name;
    @Column
    private float effect;
    @ManyToMany(mappedBy = "privileges")
    private List<Worker> workers = new ArrayList<>();

    public Privilege() {
    }

    public Privilege(String name, float effect, List<Worker> workers) {
        this.name = name;
        this.effect = effect;
        this.workers = workers;
    }

    public Privilege(String name, float effect) {
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

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
