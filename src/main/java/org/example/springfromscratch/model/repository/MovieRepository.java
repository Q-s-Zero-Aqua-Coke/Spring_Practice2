package org.example.springfromscratch.model.repository;

import org.example.springfromscratch.model.dto.MovieDTO;
import org.example.springfromscratch.model.dto.MovieParam;
import org.example.springfromscratch.model.dto.MovieResponse;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Repository
public class MovieRepository implements APIClientRepository {
    final String baseURL = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/";
    final String apiKey = dotenv.get("MOVIE_API_KEY");

    public List<MovieDTO> getMovies(MovieParam param) throws Exception {
        String action = "searchDailyBoxOfficeList";
        String format = "json";
        String url = "%s/%s.%s?key=%s&targetDt=%s".formatted(baseURL, action, format, apiKey, param.targetDate());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            MovieResponse movieResponse = objectMapper.readValue(response.body(), MovieResponse.class);
            return movieResponse.boxOfficeResult().dailyBoxOfficeList()
                    .stream().map((v) -> new MovieDTO(Long.parseLong(v.rank()), v.movieCd(), v.movieNm(), v.openDt(), Long.parseLong(v.audiAcc()))).toList();
        }

        throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
    }
}
