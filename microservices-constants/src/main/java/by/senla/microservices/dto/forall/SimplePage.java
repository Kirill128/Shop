package by.senla.microservices.dto.forall;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimplePage<T> {
    private List<T> results;
    private long countInDb;

    private int currentPageNum;
    private int nextPageNum;
    private int previousPageNum;

    public SimplePage(List<T> results) {
        this.results = results;
    }
}
