package co.com.sofka.model.budget;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Budget {

    private String id;
    private String name;
    private long initialDate;
    private long finishDate;
    private String corporateId;
    private long current;
    private long consumed;
    private long balance;
    private String status;
    private String creationUser;
    private long creationDate;
    private long modificationDate;
    private String modificationUser;
    private Boolean isSponsored;
}
