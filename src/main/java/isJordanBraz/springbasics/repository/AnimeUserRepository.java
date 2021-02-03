package isJordanBraz.springbasics.repository;

import isJordanBraz.springbasics.domain.AnimeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeUserRepository extends JpaRepository<AnimeUser, Long> {

    AnimeUser findByUsername(String username);
}
