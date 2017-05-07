package repositories;

import domain.Chorbi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Likes;

import java.util.Collection;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {

    @Query("select l.recipent from Likes l where l.recipent=?1 and l.sender=?2")
    Chorbi findChorbiByLikes(Chorbi c, Chorbi principal);

    @Query("select l from Likes l where l.recipent=?1 and l.sender=?2")
    Likes findLikesByChorbies(Chorbi c, Chorbi principal);

    @Query("select l.sender from Chorbi  c join c.userLikedYou l where c=?1")
    Collection<Chorbi> findChorbiesLikedPrincipal(Chorbi chorbi);

}
