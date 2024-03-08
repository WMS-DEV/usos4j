package dev.wms.usos4j.model.apiref;

import java.util.List;

public record UsosApiRefModule(String name, String title, String briefDescription, String description,
                               List<String> submodules, List<String> methods, boolean beta) {
}
