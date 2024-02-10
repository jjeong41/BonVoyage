//package ArtBridge.ArtBridgelogin.service;
//
//import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
//import ArtBridge.ArtBridgelogin.domain.Artist;
//import ArtBridge.ArtBridgelogin.domain.Auction;
//import ArtBridge.ArtBridgelogin.domain.Item;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@Transactional
//public class AuctionServiceTest {
//
//    @Autowired
//    AuctionService auctionService;
//
//    @Autowired
//    ItemService itemService;  // 추가
//
//    @Autowired
//    ArtistService artistService;  // 추가
//
//    private Auction auction;
//
//    @BeforeEach
//    public void setup() {
//        ArtistDto artistDto = new ArtistDto();
//        artistDto.setName("Test Artist");
//        artistDto.setId("testArtistId");
//        artistDto.setPw("testArtistPwd");
//        artistDto.setNickName("testNickname");
//        artistDto.setEmail("testArtistEmail@test.com");
//        artistDto.setContact("01012345678");
//
//        ArtistDto createdArtist = artistService.createArtist(artistDto);
//
//
//
//
//        Artist artist = convertToEntity(createdArtist);  // Artist 저장
//
//        Item item = new Item();
//        item.setItemName("Test Item");
//        item.setItemWidth(10);
//        item.setItemHeight(10);
//        item.setItemLike(0);
//        item.setItemIsSold(false);
//        item.setItemCreatedDate(LocalDateTime.now());
//        item.setArtist(artist);  // Artist 설정
//        item = itemService.createItemDto(item);  // Item 저장
//
//        auction = new Auction();
//        auction.setAuctionScheduledTime(LocalDateTime.now());
//        auction.setAuctionStatus(1);
//        auction.setAuctionStartPoint(1000);
//        auction.setAuctionAskPoint(10);
//        auction.setAuctionIsMiscarried(false);
//        auction.setAuctionSellPoint(2000L);
//        auction.setAuctionCreatedDate(LocalDateTime.now());
//        auction.setItem(item);
//        auction = auctionService.createAuction(auction);
//    }
//
//    @Test
//    void readOneTest() {
//        Auction foundAuction = auctionService.readOne(auction.getAuctionSeq());
//        assertNotNull(foundAuction);
//        assertEquals(1, foundAuction.getAuctionStatus());
//    }
//
//    @Test
//    void updateAuctionTest() {
//        Auction updatedAuction = new Auction();
//        updatedAuction.setAuctionScheduledTime(LocalDateTime.now());
//        updatedAuction.setAuctionStatus(2);
//        updatedAuction.setAuctionStartPoint(1000);
//        updatedAuction.setAuctionAskPoint(10);
//        updatedAuction.setAuctionSellPoint(2000L);
//        Auction result = auctionService.updateAuction(auction.getAuctionSeq(), updatedAuction);
//
//        assertNotNull(result);
//        assertEquals(2, result.getAuctionStatus());
//    }
//
//    @Test
//    void deleteAuctionTest() {
//        auctionService.deleteAuction(auction.getAuctionSeq());
//        Auction foundAuction = auctionService.readOne(auction.getAuctionSeq());
//        assertNull(foundAuction);
//    }
//
//    private Artist convertToEntity(ArtistDto artistDto) {
//        Artist artist = new Artist();
//        artist.setArtistId(artistDto.getId());
//        artist.setArtistName(artistDto.getName());
//        artist.setArtistPwd(artistDto.getPw());
//        artist.setArtistNickname(artistDto.getNickName());
//        artist.setArtistEmail(artistDto.getEmail());
//        artist.setArtistContact(artistDto.getContact());
//        // 다른 필드들도 필요에 따라 추가
//
//        return artist;
//    }
//}