package repositories;

import domain.Chorbi;
import domain.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SearchTemplate;

import java.util.Collection;

@Repository
public interface SearchTemplateRepository extends
		JpaRepository<SearchTemplate, Integer> {

	@Query("select c from Chorbi c where ((c.age>?1 and c.age<?2) or ?1 is null and ?2 is null ) and (c.gender=?3 or ?3 is null) and " +
			"(c.relationship=?4 or ?4 is null) or (c.coordinates=?5 or ?5 is null)")
	Collection<Chorbi> search(Integer minage, Integer maxager, Chorbi.Gender gender, Chorbi.Relationship relationship, Coordinates coordinates);

}
