package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.OpenVidu.Meeting;
import ArtBridge.ArtBridgelogin.domain.OpenVidu.QMeeting;
import ArtBridge.ArtBridgelogin.domain.QArtist;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingRepository {

    private final EntityManager em;
    private QMeeting qMeeting = QMeeting.meeting;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    public Meeting create(Meeting meeting) {
        em.persist(meeting);
        return meeting;
    }

    public Meeting readMeetingBySeq(int meetingSeq) {
        return queryFactory
                .selectFrom(qMeeting)
                .where(qMeeting.meetingSeq.eq(meetingSeq))
                .fetchOne();
    }

    public Meeting readMeetingBySession(String sessionId) {
        return queryFactory
                .selectFrom(qMeeting)
                .where(qMeeting.meetingSessionId.eq(sessionId))
                .fetchOne();
    }

    public void updateBySeq(int meetingSeq, String newSessionId) {
        queryFactory
                .update(qMeeting)
                .set(qMeeting.meetingSessionId, newSessionId)
                .where(qMeeting.meetingSeq.eq(meetingSeq))
                .execute();
    }

    public void deleteMeetingBySession(String sessionId) {
        queryFactory
                .delete(qMeeting)
                .where(qMeeting.meetingSessionId.eq(sessionId))
                .execute();
    }
}
