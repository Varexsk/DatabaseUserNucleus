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
public class Warp {

    private User user;
    private String warp_name;
    private int warp_count;

    public Warp(User user, String warp_name, int warp_count) {
        this.user = user;
        this.warp_name = warp_name;
        this.warp_count = warp_count;
    }
}
