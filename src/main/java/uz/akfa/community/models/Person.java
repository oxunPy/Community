package uz.akfa.community.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Table(name = "people")
public class Person implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private Short age;

//    private String status_code;

    @Enumerated(value = EnumType.STRING)
    private Status status;

//    private String gender_code;

    @Enumerated(value = EnumType.STRING)
    private GenderType gender; //(sex)

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "community_id", referencedColumnName = "id")
    private Community personCommunity;

    public Person() {
    }

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public String toString(){
        return firstName + " " + lastName;
    }


    @Override
    public boolean equals(Object obj){
        if(obj == null || !(obj instanceof Person)) return false;
        else if(obj == this) return true;

        Person that = (Person) obj;
        return this.id == that.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
