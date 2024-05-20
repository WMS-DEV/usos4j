package pl.wmsdev.usos4j.model.apiref;

import java.util.List;

public record UsosApiRefMethod(String name, String shortName, String description, String briefDescription,
                               String refUrl, UsosApiRefMethodAuthOptions authOptions,
                               List<UsosApiRefMethodArgument> arguments, String returns, String errors,
                               List<UsosApiRefMethodReturnFields> resultFields, Boolean beta,
                               UsosApiRefMethodDepreciation deprecated, Boolean adminAccess, Boolean isInternal) {
}
