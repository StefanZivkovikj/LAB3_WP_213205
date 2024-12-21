package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByAlbum_Id(Long albumId);

    @Query("SELECT MAX(CAST(SUBSTRING(s.trackId, 2) AS int)) FROM Song s")
    String findMaxTrackId();

    @Query("SELECT DISTINCT s.releaseYear FROM Song s")
    List<Integer> findDistinctReleaseYears();

    @Query("SELECT DISTINCT s.genre FROM Song s")
    List<String> findDistinctGenres();

    @Query("SELECT DISTINCT s.title FROM Song s")
    List<String> findDistinctTitles();

    @Query("SELECT DISTINCT s.price FROM Song s")
    List<Double> findDistinctPrices();

    @Query("SELECT s FROM Song s WHERE " +
            "(:albumId IS NULL OR s.album.id = :albumId) AND " +
            "(:releaseYear IS NULL OR s.releaseYear = :releaseYear) AND " +
            "(:genre IS NULL OR s.genre = :genre) AND " +
            "(:title IS NULL OR s.title = :title) AND " +
            "(:price IS NULL OR s.price = :price)")
    List<Song> filterSongs(@Param("albumId") Long albumId,
                           @Param("releaseYear") Integer releaseYear,
                           @Param("genre") String genre,
                           @Param("title") String title,
                           @Param("price") Double price);

    List<Song> findAllByReleaseYear(Integer releaseYear);


//    List<Song> findAllByReleaseYear(Integer releaseYear);

}
