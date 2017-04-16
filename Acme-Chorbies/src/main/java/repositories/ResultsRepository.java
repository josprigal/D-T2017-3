package repositories;

import domain.SearchTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Results;

@Repository
public interface ResultsRepository extends JpaRepository<Results, Integer> {

    @Query("select r from Results r where r.searchTemplate=?1")
    Results findResultsBySearchTemplate(SearchTemplate searchTemplate);

}
