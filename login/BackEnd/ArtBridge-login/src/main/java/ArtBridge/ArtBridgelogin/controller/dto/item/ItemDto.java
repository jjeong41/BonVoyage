package ArtBridge.ArtBridgelogin.controller.dto.item;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
import lombok.Data;

@Data
public class ItemDto {
    private String itemName;
    private int itemWidth;
    private int itemHeight;
    private int itemLike;
    private Long itemSellPrice;
    private boolean itemIsSold;
    private String artistId;

}