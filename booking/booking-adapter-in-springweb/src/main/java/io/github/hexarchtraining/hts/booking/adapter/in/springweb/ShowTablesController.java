package io.github.hexarchtraining.hts.booking.adapter.in.springweb;

import io.github.hexarchtraining.hts.booking.port.in.ShowTablesQuery;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesResult;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("booking")
public class ShowTablesController {

    private final ShowTablesPort showTablesPort;

    @GetMapping(value = "/tables")
    public List<ShowTablesResult> showTables() {
        // in the future, ShowTablesQuery may contain something interesting
        return showTablesPort.showTables(new ShowTablesQuery());
    }
}
