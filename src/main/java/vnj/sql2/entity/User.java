package vnj.sql2.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class User {

    private int id;
    private String name;
    private int warp;
    private int rtp;

    public User(String name, int warp, int rtp) {
        this.name = name;
        this.warp = warp;
        this.rtp = rtp;
    }

}
