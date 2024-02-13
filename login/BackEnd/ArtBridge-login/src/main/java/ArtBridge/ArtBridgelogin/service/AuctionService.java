package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistMentionDto;
import ArtBridge.ArtBridgelogin.controller.dto.auction.AuctionDto;
import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.repository.AuctionRepository;
import ArtBridge.ArtBridgelogin.repository.ItemRepository;
import ArtBridge.ArtBridgelogin.service.errorMessage.MyDataAccessException;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;

    @Transactional
    public AuctionDto createAuction(AuctionDto auctionDto) {
        try {
            Auction auction = new Auction();

            auction.setAuctionSessionId(auctionDto.getAuctionSessionId());
            auction.setAuctionScheduledTime(auctionDto.getAuctionScheduledTime());
            auction.setAuctionStatus(auctionDto.getAuctionStatus());
            auction.setAuctionStartPoint(auctionDto.getAuctionStartPoint());
            auction.setAuctionAskPoint(auctionDto.getAuctionAskPoint());
            auction.setAuctionCreatedDate(auctionDto.getAuctionCreatedDate());
            return convertToDto(auctionRepository.create(auction));
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Failed to create auction", e);
        }
    }


    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<AuctionDto> readAllAuction(){
        try {
            List<Auction> auctions = auctionRepository.readAll();

            if (auctions.isEmpty()) {
                throw new NoDataFoundException("No artist mentions found");
            }

            return convertToDtoList(auctions);
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Failed to read all artist mentions", e);
        }
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public AuctionDto readOne(int seq) {
        Auction auction = auctionRepository.readOne(seq);

        if (auction == null) {
            throw new NoDataFoundException("Seq가 " + seq + "인 아티스트 멘션을 찾을 수 없습니다.");
        }

        return convertToDto(auction);
    }


//    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
//    public ArtistDto readAuctionByArtistSeq(int seq) {
//        Item item =  auctionRepository.readItemByAuctionSeq(seq);
//        Artist artist = auctionRepository.readArtistByItemSeq(item.getItemSeq());
//
//        if (artist == null) {
//            throw new NoDataFoundException("Artist not found with ID: " + item.getItemSeq());
//        }
//
//        return convertToDto(artist);
//    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Item readItemByAuctionSeq(int seq) {
        return auctionRepository.readItemByAuctionSeq(seq);
    }
    @Transactional
    public List<Auction> readAuctionsBySameAuthor(Long authorId) {
        return auctionRepository.readAuctionsBySameAuthor(authorId);
    }


    //Todo: UPDATE
    @Transactional
    public AuctionDto updateAuction(int seq, AuctionDto updatedAuctionDto) {
        Auction auction = auctionRepository.readOne(seq);

        if(auction == null) {
            throw new NoDataFoundException("auction을 찾을 수 없습니다.");
        }
        BeanUtils.copyProperties(updatedAuctionDto, auction, "auctionSeq");

        auctionRepository.updateAuction(seq, auction);
        return convertToDto(auctionRepository.readOne(seq));
    }


    //Todo: DELETE
    @Transactional
    public void deleteAuction(int seq) {
        auctionRepository.deleteById(seq);
    }

    private Auction convertToEntity(AuctionDto auctionDto) {
        Auction auction = new Auction();
        auction.setAuctionSessionId(auctionDto.getAuctionSessionId());
        auction.setAuctionScheduledTime(auctionDto.getAuctionScheduledTime());
        auction.setAuctionStatus(auctionDto.getAuctionStatus());
        auction.setAuctionStartPoint(auctionDto.getAuctionStartPoint());
        auction.setAuctionAskPoint(auctionDto.getAuctionAskPoint());
        auction.setAuctionCreatedDate(auctionDto.getAuctionCreatedDate());

        return auction;
    }

    private AuctionDto convertToDto(Auction auction) {
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setAuctionSessionId(auction.getAuctionSessionId());
        auctionDto.setAuctionScheduledTime(auction.getAuctionScheduledTime());
        auctionDto.setAuctionStatus(auction.getAuctionStatus());
        auctionDto.setAuctionStartPoint(auction.getAuctionStartPoint());
        auctionDto.setAuctionAskPoint(auction.getAuctionAskPoint());
        auctionDto.setAuctionCreatedDate(auction.getAuctionCreatedDate());
        return auctionDto;
    }

    private List<AuctionDto> convertToDtoList(List<Auction> auctions) {
        return auctions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
