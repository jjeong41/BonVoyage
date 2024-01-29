package ArtBridge.ArtBridgelogin.domain.member;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "artist")
@Data
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_seq")
    private Long aristSeq;

    @Column(name = "artist_name", length = 30, nullable = false)
    private String artistName;

    @Column(name = "artist_pwd", length = 30, nullable = false)
    private String artistPwd;

    @Column(name = "artist_nickname", length = 30, nullable = false)
    private String artistNickname;

    @Column(name = "artist_email", length = 30, nullable = false)
    private String artistEmail;

    @Column(name = "artist_contact", length = 50, nullable = false)
    private String artistContact;

    @Column(name = "artist_point", length = 11, nullable = false)
    private Long artistPoint;

    @Column(name = "artist_history", nullable = false)
    private String artistHistory;

    @Column(name = "artist_isdeleted", nullable = false)
    private boolean artistIsdeleted;

    @Column(name = "artist_deleted_date")
    private Date artistDeletedDate;

    @Column(name = "artist_created_date", nullable = false)
    private Date artistCreatedDate;

}