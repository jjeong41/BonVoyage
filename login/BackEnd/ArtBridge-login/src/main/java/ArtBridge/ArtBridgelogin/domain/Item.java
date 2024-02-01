package ArtBridge.ArtBridgelogin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="item")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_seq", nullable = false)
    private int itemSeq;

    @Column(name = "artist_seq", nullable = false)
    private int artistSeq;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "artist_seq", nullable = false)
//    private Artist artist;


    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName;

    @Column(name = "item_width", nullable = false)
    private int itemWidth;

    @Column(name = "item_height", nullable = false)
    private int itemHeight;

    @Column(name = "item_like", nullable = false)
    private int itemLike;

    @Column(name = "item_sell_price")
    private Long itemSellPrice;

    @Column(name = "item_is_sold", nullable = false)
    private boolean itemIsSold;

    @Column(name = "item_created_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime itemCreatedDate;

//    @OneToOne
//    @JoinColumn(name = "order_detail")
//    private OrderDetail orderDetail;
}
