package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.ArtistHomepageComment;
import ArtBridge.ArtBridgelogin.domain.QArtistHomepageComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ArtistHomepageCommentRepository {

    private final EntityManager em;

    private QArtistHomepageComment qArtistHomepageComment  = QArtistHomepageComment.artistHomepageComment;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }
    @Transactional
    public ArtistHomepageComment create(ArtistHomepageComment artistHomepageComment){
        em.persist(artistHomepageComment);
        return artistHomepageComment;
    }

    @Transactional(readOnly = true)
    public ArtistHomepageComment findOne(Long seq){
        return queryFactory
                .selectFrom(qArtistHomepageComment)
                .where(qArtistHomepageComment.artistHomepageCommentSeq.eq(seq))
                .fetchOne();
    }

    @Transactional(readOnly = true)
    public List<ArtistHomepageComment> findAll(){
        return queryFactory
                .selectFrom(qArtistHomepageComment)
                .fetch();
     }

    @Transactional
    public void deleteBySeq(Long seq) {
        queryFactory
                .delete(qArtistHomepageComment)
                .where(qArtistHomepageComment.artistHomepageCommentSeq.eq(seq))
                .execute();
    }

    @Transactional
    public ArtistHomepageComment updateArtistHomepageComment(Long seq, ArtistHomepageComment updatedComment) {
        queryFactory
                .update(qArtistHomepageComment)
                .where(qArtistHomepageComment.artistHomepageCommentSeq.eq(seq))
                .set(qArtistHomepageComment.artistHompageCommentContent, updatedComment.getArtistHompageCommentContent())
                .execute();

        return queryFactory.selectFrom(qArtistHomepageComment)
                .where(qArtistHomepageComment.artistHomepageCommentSeq.eq(seq))
                .fetchOne();
    }
}