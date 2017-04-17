
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chorbi;

@Repository
public interface ChorbiRepository extends JpaRepository<Chorbi, Integer> {

	@Query("select min(c.age) from Chorbi c")
	Integer minimunAgeChorbies();
	@Query("select max(c.age) from Chorbi c")
	Integer maximunAgeChorbies();
	@Query("select avg(c.age) from Chorbi c")
	Double avgAgeChorbies();

	//	Double ratioNotCreditCard();

	//	Double ratioLove();

	//	Double ratioFriendship();

	//	Double ratioActivities();
	@Query("select min(c.receivedLikes.size) from Chorbi c")
	Integer minLikesChorbi();
	@Query("select max(c.receivedLikes.size) from Chorbi c")
	Integer maxLikesChorbi();
	@Query("select avg(c.receivedLikes.size) from Chorbi c")
	Double avgLikesChorbi();
	@Query("select min(c.sentChirps.size) from Chorbi c")
	Integer minChirpsChorbiSent();
	@Query("select max(c.sentChirps.size) from Chorbi c")
	Integer maxChirpsChorbiSent();
	@Query("select avg(c.sentChirps.size) from Chorbi c")
	Double avgChirpsChorbiSent();
	@Query("select min(c.receivedChirps.size) from Chorbi c")
	Integer minChirpsChorbiReceived();
	@Query("select avg(c.receivedChirps.size) from Chorbi c")
	Double avgChirpsChorbiReceived();
	@Query("select max(c.receivedChirps.size) from Chorbi c")
	Integer maxChirpsChorbiReceived();

	//	Integer numberOfChorbiesPerCity();

	//	Integer numberOfChorbiesPerCountry();
	@Query("select u from Chorbi u order by u.receivedLikes.size")
	Collection<Chorbi> listChorbiesNumberOfLikes();
	@Query("select u from Chorbi u order by u.receivedChirps.size")
	Collection<Chorbi> listChorbiesMoreChirpsReceived();
	@Query("select u from Chorbi u order by u.sentChirps.size")
	Collection<Chorbi> listChorbiesMoreChirpsSent();

}
