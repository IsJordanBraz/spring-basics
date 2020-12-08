package isJordanBraz.springbasics.util;

import isJordanBraz.springbasics.domain.Anime;

import java.util.List;

public class AnimeCreator {
    public static Anime createAnimeTobeSaved() {
        return new Anime(null,"Jor");
    }
    public static Anime createValidAnime() { return new Anime(1L,"jordan"); }
    public static Anime createValidUpdatedAnime() {
        return new Anime(1L,"jordanbraz");
    }
}
