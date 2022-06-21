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
import switch2021.project.dataModel.REST.UserProfileRestDTO;
import java.util.Collections;
import java.util.List;


@Repository
public class UserProfileRestRepository {

    public List<UserProfileRestDTO> findAll() {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8090"))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())))
                .build();

        UserProfileRestDTO userProfileRestDTO;

        try {
            userProfileRestDTO = webClient
                    .get()
                    .uri("/profiles/")
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                    .bodyToMono(UserProfileRestDTO.class)

                    .onErrorReturn( null )

                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} )
                    .block();
        }
        catch( Exception e) {
            userProfileRestDTO = null;
        }

        if(userProfileRestDTO != null)
            return List.of(userProfileRestDTO);
        else
            return
            Collections.emptyList();
    }
}
