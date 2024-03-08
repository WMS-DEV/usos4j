package pl.wmsdev.usos4j.model.photos;

import pl.wmsdev.usos4j.model.common.UsosErrorMessage;

import java.util.List;

public record UsosPhotosCanSeePhoto(Boolean canSeePhoto, List<UsosErrorMessage> errors) {

}
