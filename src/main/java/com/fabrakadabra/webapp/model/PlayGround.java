package com.fabrakadabra.webapp.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayGround {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private Instant createdAt;
    private String descripton;
    @OneToMany
    private List<PlayGroundImg> playGroundImgs;
    @OneToOne
    private Dimensions dimensions;
}
