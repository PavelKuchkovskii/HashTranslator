package org.kucher.hashtranslatorservice.service;

import org.kucher.hashtranslatorservice.dao.api.IAppHashDao;
import org.kucher.hashtranslatorservice.dao.entity.AppHash;
import org.kucher.hashtranslatorservice.dao.entity.enums.EAppStatus;
import org.kucher.hashtranslatorservice.service.api.IAppHashService;
import org.kucher.hashtranslatorservice.service.dto.AppHashDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppHashHandler {

    private final String EMAIL = "frizpk@gmail.com";
    private final String SECRET_CODE = "8f003ebdd17d7f9c";
    private final String URI_DECODER = "https://md5decrypt.net/en/Api/api.php";
    private final IAppHashDao dao;
    private final IAppHashService service;

    public AppHashHandler(IAppHashDao dao, IAppHashService service) {
        this.dao = dao;
        this.service = service;
    }

    @Scheduled(fixedDelay = 10000)
    public void handle() throws URISyntaxException, IOException, InterruptedException {
        Optional<List<AppHash>> appHashes = dao.findByStatus(EAppStatus.LOADED);

        if(appHashes.isPresent()) {
            List<AppHash> appHashList = appHashes.get();

            for (AppHash appHash : appHashList) {

                String result = decode(appHash.getHash());

                AppHashDTO dto = new AppHashDTO();
                dto.setResult(result);
                dto.setStatus(EAppStatus.DECODED);

                service.update(appHash.getUuid(), dto);
            }
        }
    }

    private String decode(String hashes) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URI_DECODER + "?" + "hash=" + hashes + "&hash_type=md5&email=" + EMAIL + "&code=" + SECRET_CODE))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return adjustResult(response.body());
    }

    private String adjustResult(String result) {

        StringBuilder stringBuilder = new StringBuilder();

        //Can get empty string
        if(result.isBlank()) {
            return result;
        }
        else {
            List<String> results = new ArrayList<>(List.of(result.split(";")));

            for (String s : results) {

                //Can get ERROR OR empty string
                if(s.isBlank() || s.contains("ERROR")) {
                    stringBuilder.append(" ;");
                }
                else {
                    stringBuilder.append(s);
                    stringBuilder.append(";");
                }
            }

        }

        return stringBuilder.toString();
    }

}
