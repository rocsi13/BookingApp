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
    private String latitude;

    @Column
    private String longitude;

    @Column
    private String address;

    @Column
    private String hotelPhone;

    @Column
    private List<RoomType> roomType;

}
