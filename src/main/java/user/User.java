package user;

import com.google.gson.Gson;
import lombok.Data;
import user.parameters.Address;
import user.parameters.Company;

import java.io.FileReader;
import java.io.IOException;

@Data
public class User {
    public int id;
    public String name;
    public String username;
    public String email;
    public Address address;
    public String phone;
    public String website;
    public Company company;

    public static User createUserFromJson(String filePath) {
        Gson gson = new Gson();
        User user = new User();
        try (FileReader fileReader = new FileReader(filePath)) {
            user = gson.fromJson(fileReader, User.class);
            System.out.println("Створений дефолтний користувач:");
            System.out.println(user);
            System.out.println("------------");


        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }
}
