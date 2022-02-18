package user.parameters;

import lombok.Data;

@Data
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", catchPhrase='" + catchPhrase + '\'' +
                ", bs='" + bs +
                "'}";
    }
}
