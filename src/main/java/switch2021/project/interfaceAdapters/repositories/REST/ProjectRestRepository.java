package switch2021.project.interfaceAdapters.repositories.REST;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import switch2021.project.dataModel.REST.ProjectRestDTO;

import java.util.Collections;
import java.util.List;


@Repository
public class ProjectRestRepository {

    public static final String ENDPOINT = "http://localhost:8090";
    public static final String COLLECTION = "/projects/";

    public List<ProjectRestDTO> findAll() {

        WebClient webClient = WebClient.builder()
                .baseUrl(ENDPOINT)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8090"))
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection())))
                .build();
        ProjectRestDTO projectRestDTO;
        try {
            projectRestDTO = webClient
                    .get()
                    .uri(COLLECTION)
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })

                    .bodyToMono(ProjectRestDTO.class)

                    .onErrorReturn(null)

                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    })
                    .block();
        } catch (Exception e) {
            projectRestDTO = null;
        }

        if (projectRestDTO != null)
            return List.of(projectRestDTO);
        else
            return
                    Collections.emptyList();
    }
}

