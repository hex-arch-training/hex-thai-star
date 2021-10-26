package io.github.hexarchtraining.hts.booking.adapter.in.quarkusweb;

import io.github.hexarchtraining.hts.booking.port.in.ShowTablesPort;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesQuery;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesResult;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@RequiredArgsConstructor
@Path("booking")
public class ShowTablesController {

    private final ShowTablesPort showTablesPort;

    @GET
    @Path("/tables")
    public List<ShowTablesResult> showTables() {
        // in the future, ShowTablesQuery may contain something interesting
        return showTablesPort.showTables(new ShowTablesQuery());
    }
}
