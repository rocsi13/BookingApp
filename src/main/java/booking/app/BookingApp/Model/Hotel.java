package booking.app.BookingApp.Model;

import booking.app.BookingApp.Enum.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    @Column(unique = true)
    private String name;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private String hotelAddress;

    @Column
    private String hotelPhone;

    @Column
    private List<RoomType> roomType;

    @Column
    private Long distance = 0L;



}
