package mk.ukim.finki.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.repository.inMemoryArtistRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final inMemoryArtistRepository artistRepository;

    public SongServiceImpl(SongRepository songRepository, inMemoryArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public void addSong(Song song) {

    }

    @Override
    public Song findById(Long songId) {
        return songRepository.findById(songId).orElse(null);
    }

    @Override
    public void removeSong(Long id) {

    }

    @Override
    public void deleteByTrackId(String trackId) {

    }

    @Override
    public void update(Song song) {

    }

    @Override
    public void save(Song song) {
        if (song.getTrackId() == null || song.getTrackId().isEmpty()) {
            song.setTrackId(String.valueOf(generateNextTrackId()));
        }
        songRepository.save(song);
    }

    @Override
    public int generateNextTrackId() {
        return (int) (songRepository.count() + 1);
    }

    @Override
    public List<Song> findAllByAlbum_Id(Long albumId) {
        return null;
    }

    @Override
    public List<Song> findAllByReleaseYear(Integer releaseYear) {
        return null;
    }

    @Override
    public String findMaxTrackId() {
        return null;
    }

    @Override
    public List<Song> filterSongs(Long albumId, Integer releaseYear, String genre, String title, Double price) {
        return songRepository.filterSongs(albumId, releaseYear, genre, title, price);
    }

    @Override
    public List<Integer> findDistinctReleaseYears() {
        return songRepository.findDistinctReleaseYears();
    }

    @Override
    public List<String> findDistinctGenres() {
        return songRepository.findDistinctGenres();
    }

    @Override
    public List<String> findDistinctTitles() {
        return songRepository.findDistinctTitles();
    }

    @Override
    public List<Double> findDistinctPrices() {
        return songRepository.findDistinctPrices();
    }

    @Override
    public List<Song> listSongs() {
        return null;
    }

    @Transactional
    public void addArtistToSong(Artist artist, Song song) {
        if (artist != null && song != null && !song.getPerformers().contains(artist)) {
            song.getPerformers().add(artist);
            songRepository.save(song);
        }
    }

    @Override
    public Song findByTrackId(String trackId) {
        return null;
    }
}
