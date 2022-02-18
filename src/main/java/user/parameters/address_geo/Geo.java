package user.parameters.address_geo;

import lombok.Data;

@Data
public class Geo {
    private String lat;
    private String lng;

    @Override
    public String toString() {
        return "{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng +
                "'}";
    }
}
